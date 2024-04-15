package com.epms.employee.controller;

import com.epms.employee.model.response.EntityResponse;
import com.epms.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @GetMapping("/getEmployeeById")
    public ResponseEntity<?> getEmployeeById(@RequestParam Long employeeId){
        return new ResponseEntity<>(new EntityResponse(iEmployeeService.getEmployeeById(employeeId),0), HttpStatus.OK);
    }
}
