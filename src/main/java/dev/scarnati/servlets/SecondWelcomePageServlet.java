package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Info;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecondWelcomePageServlet extends HttpServlet {

    EmployeeService employeeService = new EmployeeService();
    RequestRepo requestRepo = new RequestRepo();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        int id = (int) session.getAttribute("currentUser");
        Employee employee = employeeService.getEById(id);
        if(employee != null){
            response.setStatus(200);
            response.getWriter().write(om.writeValueAsString(employee));
        }
        else{
            response.setStatus(400);
        }

    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Info i = (om.readValue(request.getReader(), Info.class));
        int rId = i.getId();
        Requests requests = requestRepo.getByReqId(rId);
        String file = i.getInfo();
        requests.setFile(file);
        boolean updateFile = requestRepo.addFile(requests);
                if (updateFile) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
        }

}
