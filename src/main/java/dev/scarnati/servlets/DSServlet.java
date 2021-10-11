package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Info;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.ApprovalRepo;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DSServlet extends HttpServlet {
    ApprovalRepo approvalRepo = new ApprovalRepo();
    ObjectMapper om = new ObjectMapper();
    RequestRepo requestRepo = new RequestRepo();
    EmployeeRepo employeeRepo = new EmployeeRepo();
    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Approval a = (om.readValue(request.getReader(), Approval.class));
        boolean deny = a.isDenied();
        System.out.println(a);
        int id = a.getId();
        Requests requests = requestRepo.getByReqId(id);
        boolean complete = requests.isComplete();
        if (deny && !complete) {
            //Deny the request


            requests.setDenial(deny);
            //change pending balance
            float requestAmount = requests.getReimbursementAmount();
            int eid = requests.getEmployeeId();
            Employee employee = employeeService.getEById(eid);
            float pendingAmount = employee.getPendingAmount();
            float newAmount = pendingAmount - requestAmount;
            employee.setPendingAmount(newAmount);
            //apply changes
            boolean updateE = employeeRepo.updatePending(employee);
            boolean updateR = requestRepo.denyReq(requests);
            boolean updateA = approvalRepo.deny(a);

            //execute
            if (updateA && updateR && updateE) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }
         else{
            boolean updateA = approvalRepo.updateApprovalDs(a);
            if (updateA) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Info i = (om.readValue(request.getReader(), Info.class));
        int aId = i.getId();
        Approval a = approvalRepo.getApprovalById(aId);
        String info = i.getInfo();
        a.setDsInfo(info);
        boolean updateDs = approvalRepo.UpdateDSInfo(a);
        if (updateDs) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}


