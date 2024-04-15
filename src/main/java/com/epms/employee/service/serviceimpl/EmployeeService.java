package com.epms.employee.service.serviceimpl;

import com.epms.employee.exception.EmployeeNotFoundException;
import com.epms.employee.repository.EmployeeRepository;
import com.epms.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Object getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee Not found"));
    }
}
