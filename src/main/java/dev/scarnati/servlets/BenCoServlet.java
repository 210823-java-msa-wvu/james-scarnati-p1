package dev.scarnati.servlets;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.models.Update;
import dev.scarnati.repositories.ApprovalRepo;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BenCoServlet extends HttpServlet {

    ObjectMapper om = new ObjectMapper();
    EmployeeService employeeService = new EmployeeService();
    RequestRepo requestRepo = new RequestRepo();
    EmployeeRepo employeeRepo = new EmployeeRepo();
    ApprovalRepo approvalRepo = new ApprovalRepo();


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {

        Approval a = (om.readValue(request.getReader(), Approval.class));
        boolean deny = a.isDenied();
        boolean approved = a.isBcApproval();
        System.out.println(a);
        int id = a.getId();
        Approval b = approvalRepo.getApprovalById(id);
        boolean dh = b.isDhApproval();
        boolean ds = b.isDsApproval();
        Requests requests = requestRepo.getByReqId(id);
        boolean complete = requests.isComplete();
        int eid = requests.getEmployeeId();
        Employee employee = employeeService.getEById(eid);
        if(deny && !complete){
            //Deny the request
            requests.setDenial(deny);
            //change pending balance
            float requestAmount = requests.getReimbursementAmount();

            float pendingAmount = employee.getPendingAmount();
            float newAmount = pendingAmount - requestAmount;
            employee.setPendingAmount(newAmount);
            //apply changes
            boolean updateE = employeeRepo.updatePending(employee);
            boolean updateR = requestRepo.denyReq(requests);
            boolean updateA = approvalRepo.deny(a);

            //execute
            if (updateA && updateR&& updateE) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }
        else if (approved && eid == 3) {
            requests.setApproval(approved);
            a.setDsApproval(approved);
            a.setDhApproval(approved);
            boolean updateR = requestRepo.approveReq(requests);



            boolean updateA = approvalRepo.updateApprovalBcForDh(a);
            if (updateA) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }

        }
        else if (approved) {

            requests.setApproval(approved);
            boolean updateR = requestRepo.approveReq(requests);



            boolean updateA = approvalRepo.updateApprovalBc(a);
            if (updateA) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }

            }
        else{
            boolean updateA = approvalRepo.updateApprovalBc(a);
            if (updateA){
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi");

        //request info
        Update u = (om.readValue(request.getReader(), Update.class));
        System.out.println(u);
        float newAmount = u.getAmount();

        int reqId = u.getRequest();

        //original request info to new info
        Requests requests = requestRepo.getByReqId(reqId);
        boolean complete = requests.isComplete();
        if (!complete) {
            float originalAmount = requests.getReimbursementAmount();//to change the employees pending amount
            System.out.println(requests);
            requests.setReimbursementAmount(newAmount);
            System.out.println(requests);
            //original employee info to new info
            Employee employee = employeeService.getEById(requests.getEmployeeId());
            float pending = employee.getPendingAmount();
            float newPending = pending - originalAmount;
            float newTotal = newAmount + newPending;
            employee.setPendingAmount(newTotal);
            System.out.println(employee);
            //send update to database
            boolean updateE = employeeRepo.updatePending(employee);
            boolean updateR = requestRepo.updateAmount(requests);

            if (updateE) {
                if (updateR) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }
    else {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }


}
