package dev.scarnati.services;

import dev.scarnati.models.Employee;
import dev.scarnati.repositories.EmployeeRepo;

import java.util.Objects;

public class LoginService {
    EmployeeRepo employeeRepo = new EmployeeRepo();

    public boolean login(String username, String password) {


        Employee a = employeeRepo.getByUsername(username);
        if (a != null) {
            return username.equals(a.getUsername()) && password.equals(a.getPass());
        }
        return false;
    }
}

