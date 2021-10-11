package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
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

public class EmployeeServlet extends HttpServlet {
    ObjectMapper om = new ObjectMapper();
    RequestRepo requestRepo = new RequestRepo();
    EmployeeService employeeService = new EmployeeService();
    EmployeeRepo employeeRepo = new EmployeeRepo();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Requests a = (om.readValue(request.getReader(), Requests.class));
        String grade = a.getGrade();
        System.out.println(a);
        int id = a.getId();
            Requests requests = requestRepo.getByReqId(id);
            requests.setGrade(grade);
            //apply changes
            boolean update = requestRepo.setGrade(requests);
            //execute
            if (update) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
    }
}
