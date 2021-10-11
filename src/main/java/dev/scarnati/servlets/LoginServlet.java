package dev.scarnati.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;

import dev.scarnati.models.Approval;
import dev.scarnati.models.Employee;

import dev.scarnati.models.Login;
import dev.scarnati.services.EmployeeService;
import dev.scarnati.services.LoginService;


import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {

        LoginService loginService = new LoginService();
        private static ObjectMapper om = new ObjectMapper();
        EmployeeService employeeService = new EmployeeService();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
            Login l = (om.readValue(request.getReader(), Login.class));

            request.getSession().invalidate();
            String username = l.getUsername();
            String password = l.getPassword();
            System.out.println(username);
            System.out.println(password);
            HttpSession session = request.getSession();


            boolean signInResponse = loginService.login(username,password);
            if (signInResponse){

                Employee a;
                a = employeeService.getEmployeeByUsername(username);

            int id = a.getId();
                String fname = a.getfName();
                String lname = a.getlName();
                String title = a.getTitle();
                String name = fname + " " + lname;
                float startingAmount = a.getStartingAmount();
                float pending = a.getPendingAmount();
                float used = a.getApprovedAmount();
                float remaining = startingAmount -(pending+used);


                System.out.println("Session Id: " + session.getId());

                session.setAttribute("currentUser", id);
                session.setAttribute("name", name);
                session.setAttribute("title", title);
                session.setAttribute("remaining", remaining);
                session.setAttribute("pending", pending);
                session.setAttribute("used", used);

//            System.out.println(session.getAttribute("currentUser"));
//            boolean employee = loginService.enterEmployee(username);
//            boolean dh = loginService.enterDepartmentHead(username);
//            boolean bc = loginService.enterBenefitsCoordinator(username);
//            boolean ds = loginService.enterDirectSupervisor(username);
//            System.out.println("E: " + employee);
//            System.out.println("dh: " + dh);
//            System.out.println("ds: " + ds);
//            System.out.println("bc: " + bc);


                    a.setPass("Private");
                    String emp = om.writeValueAsString(a);
                    System.out.println(emp);

                    Cookie user = new Cookie("user", emp);
                    user.setPath("/");
                    System.out.println(user);
                    response.addCookie(user);
                    response.setStatus(200);

                }
            else {
                response.sendRedirect("http://localhost8080:/P1/Login.html");
            }




        }
}

