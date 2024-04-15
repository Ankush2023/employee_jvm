package com.epms.employee.service;

import com.epms.employee.model.Employee;
import org.springframework.security.core.userdetails.UserDetails;

public interface IEmployeeAccessService {
    Employee findByEmail(String username);

    UserDetails loadUserByUsername(String email);
}
