package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Info;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.ApprovalRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WelcomepageServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeService();
    ApprovalRepo approvalRepo = new ApprovalRepo();

    ObjectMapper om = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        int id = (int) session.getAttribute("currentUser");
        System.out.println(id);

        List<Requests> requests = employeeService.getById(id);
//        List<Approval> approvals = null;
//        int size = requests.size();
//        for(int i = 0;i <size - 1; i++ ){
//            approvals.set(i, approvalRepo.getApprovalById(i));
//        }
//        System.out.println(approvals);
        System.out.println(requests);
        if(requests != null){
            response.setStatus(200);
//            response.getWriter().write(om.writeValueAsString(approvals));
        response.getWriter().write(om.writeValueAsString(requests));
            System.out.println(om.writeValueAsString(requests));

    }
        else{
            String send = "You Do Not Have Any Requests";
            System.out.println(send);
            response.getWriter().write(send);

        }
        


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        PrintWriter pw = response.getWriter();

        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("currentUser");
        System.out.println(id);

        List<Requests> r = employeeService.getById(id);
      //  List<Approval> approvals = new ArrayList<>();
        int size = r.size();
        System.out.println(size);



        System.out.println(r);
        if(r != null){
            System.out.println(size);
            List<Approval> approvals = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Requests a = r.get(i);

                approvals.add(approvalRepo.getApprovalById(a.getId()));

            }

            System.out.println(approvals);
            response.setStatus(200);
            response.getWriter().write(om.writeValueAsString(approvals));
//            response.getWriter().write(om.writeValueAsString(requests));
            System.out.println(om.writeValueAsString(approvals));

        }
        else{
            String send = "You Do Not Have Any Requests";
            System.out.println(send);
            response.getWriter().write(send);

        }


    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("currentUser");
        Employee employee = employeeService.getEById(id);
        String title = employee.getTitle();
        Info i = (om.readValue(request.getReader(), Info.class));
        int aId = i.getId();
        Approval a = approvalRepo.getApprovalById(aId);
        String info = i.getInfo();
        switch (title){
            case "Employee":
                a.setEmployeeInfo(info);
                boolean updateE = approvalRepo.UpdateEInfo(a);
            if (updateE) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
                break;
            case "Direct Supervisor":
                a.setDsInfo(info);
                boolean updateDs = approvalRepo.UpdateDSInfo(a);
                if (updateDs) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
                break;
            case "Department Head":
                a.setDhInfo(info);
                boolean updateDh = approvalRepo.UpdateDHInfo(a);
                if (updateDh) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
                break;
            default: break;
        }
    }


}
