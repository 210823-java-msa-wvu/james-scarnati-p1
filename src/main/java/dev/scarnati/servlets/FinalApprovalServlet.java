package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinalApprovalServlet extends HttpServlet {
    RequestRepo requestRepo = new RequestRepo();
    EmployeeRepo employeeRepo = new EmployeeRepo();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Requests requests = om.readValue(request.getReader(), Requests.class);
        boolean dsCheck = requests.isDsCheck();
        int id = requests.getId();
        Requests r = requestRepo.getByReqId(id);
        boolean complete = r.isComplete();
        if(!complete) {
            boolean approved = r.isApproval();
            r.setDsCheck(dsCheck);
            boolean check = requestRepo.dmCheck(r);
            if (dsCheck && approved && check) {
                int eId = r.getEmployeeId();
                float courseCost = r.getReimbursementAmount();
                Employee employee = employeeRepo.getById(eId);
                float aAmount = employee.getApprovedAmount();
                float pending = employee.getPendingAmount();
                pending = pending - courseCost;
                aAmount = aAmount + courseCost;
                employee.setPendingAmount(pending);
                employee.setApprovedAmount(aAmount);
                System.out.println(employee);
                boolean updateE = employeeRepo.updateApproved(employee);
                if (updateE) {
                    System.out.println("yay");
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    System.out.println("boo");
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } else if (!dsCheck && approved && check) {
                int eId = r.getEmployeeId();
                float courseCost = r.getReimbursementAmount();
                r.setDenial(true);
                boolean updateR = requestRepo.denyReq(r);
                Employee employee = employeeRepo.getById(eId);
                float pending = employee.getPendingAmount();
                pending = pending - courseCost;
                employee.setPendingAmount(pending);
                System.out.println(employee);
                boolean updateE = employeeRepo.updatePending(employee);
                if (updateE && updateR) {
                    System.out.println("yay");
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    System.out.println("boo");
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } else {
                System.out.println("boo");
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Requests requests = om.readValue(request.getReader(), Requests.class);
        boolean pass = requests.isPass();
        int id = requests.getId();
        Requests r = requestRepo.getByReqId(id);
        boolean approved = r.isApproval();
        System.out.println(approved);
        r.setPass(pass);
        boolean approveGrade = requestRepo.passOrFail(r);
        System.out.println(approveGrade);
        if(approveGrade){
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
            else{
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }

    }

}
