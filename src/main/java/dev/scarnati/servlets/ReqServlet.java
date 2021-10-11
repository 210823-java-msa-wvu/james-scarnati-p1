package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.ApprovalRepo;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReqServlet extends HttpServlet {
    ObjectMapper om = new ObjectMapper();
    RequestRepo requestRepo = new RequestRepo();
    ApprovalRepo approvalRepo = new ApprovalRepo();
    EmployeeService employeeService = new EmployeeService();
    EmployeeRepo employeeRepo = new EmployeeRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Requests> requests = requestRepo.getAllRequests();
        if(requests != null){
            response.setStatus(200);
            response.getWriter().write(om.writeValueAsString(requests));

        }
        else{
            String send = "You Do Not Have Any Requests";
            System.out.println(send);
            response.getWriter().write(send);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        List<Approval> approvals = approvalRepo.getAllApproval();
        if(!approvals.isEmpty()){
            response.setStatus(200);
            response.getWriter().write(om.writeValueAsString(approvals));

        }
        else{
            String send = "You Do Not Have Any Requests";
            System.out.println(send);
            response.getWriter().write(send);

        }

    }



    }

