package dev.scarnati.services;

import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.repositories.RequestRepo;

import java.util.ArrayList;

public class EmployeeService {
    EmployeeRepo employeeRepo = new EmployeeRepo();
    RequestRepo requestRepo = new RequestRepo();

    public Employee getEmployeeByUsername(String username){
       Employee a = employeeRepo.getEmployeeByUsername(username);
                if(a != null){
                    return a;
                }
                else return null;
    }

    public ArrayList<Requests> getById(Integer id){
        ArrayList<Requests> requests = requestRepo.getById(id);
        if(requests != null){
            return requests;
        }
        else return null;
    }
    public Employee getEById(Integer id){
        Employee employee = employeeRepo.getById(id);
        if(employee != null){
            return employee;
        }
        else {
            return null;
        }

    }
}
