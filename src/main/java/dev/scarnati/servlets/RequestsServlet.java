package dev.scarnati.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.ApprovalRepo;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;
import dev.scarnati.services.CoursesService;
import dev.scarnati.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class RequestsServlet extends HttpServlet {
    CoursesService coursesService = new CoursesService();
    RequestRepo requestRepo = new RequestRepo();
    ApprovalRepo approvalRepo = new ApprovalRepo();
    EmployeeService employeeService = new EmployeeService();
    EmployeeRepo employeeRepo= new EmployeeRepo();
    ObjectMapper om = new ObjectMapper();

    private String pattern = "yyyy-MM-dd";
    String date =new SimpleDateFormat(pattern).format(new Date());

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //get and set request info
        Requests requests = om.readValue(request.getReader(), Requests.class);
        float courseCost  = requests.getCourseCost();
        int courseType = requests.getCourseType();

        String requestDate =  date;
        String courseDate = requests.getCourseStart();
        requests.setRequestDate(requestDate);
        float costPercent = coursesService.getCostById(courseType);
        float reimbursementAmount = courseCost * costPercent;
        requests.setReimbursementAmount(reimbursementAmount);
        boolean approval = false;
        requests.setApproval(approval);
        boolean pass = false;
        requests.setPass(pass);
        boolean denial = false;
        requests.setDenial(denial);
        requests.setDsCheck(false);
        //Comparing Dates to see if its urgent
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date first = sdf.parse(requestDate);
            Date second = sdf.parse(courseDate);
            long difference = (Math.abs(second.getTime() - first.getTime()))/(1000*60*60*24);
            if (difference <= 14){
                requests.setUrgent(true);
            }
            else {
                requests.setUrgent(false);
            }
        //update employee pending balance
        int employeeId = (int) session.getAttribute("currentUser");
        requests.setEmployeeId(employeeId);
        Employee employee = employeeService.getEById(employeeId);
        float approved = employee.getApprovedAmount();
        float starting = employee.getStartingAmount();
        float pending = employee.getPendingAmount();
        pending = pending +reimbursementAmount;
        float total = approved + pending;
        if(starting >= total) {
            employee.setPendingAmount(pending);
            boolean update = employeeRepo.updatePending(employee);
            System.out.println(requests);
            boolean add = requestRepo.addRequest(requests);
            boolean app = approvalRepo.createNewApproval();
            if (add) {
                if (app) {
                    if (update) {
                        response.setStatus(204);
                    }
                }
            }
        }
        else{
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }} catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
