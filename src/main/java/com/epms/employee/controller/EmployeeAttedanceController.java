package com.epms.employee.controller;

import com.epms.employee.model.request.EmployeeAttendanceRequest;
import com.epms.employee.model.response.EntityResponse;
import com.epms.employee.service.IEmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employeeattendance")
public class EmployeeAttedanceController {
    @Autowired
    private IEmployeeAttendanceService iEmployeeAttendanceService;

    @PostMapping("saveOrUpdateEmployeeAttendance")
    public ResponseEntity<?> saveOrUpdateEmployeeAttendance(@RequestBody EmployeeAttendanceRequest employeeAttendanceRequest){
        return new ResponseEntity<>(new EntityResponse(iEmployeeAttendanceService.saveOrUpdateEmployeeAttendance(employeeAttendanceRequest),0), HttpStatus.OK);

    }
    @GetMapping("GetEmployeeAttendance")
    public ResponseEntity<?> GetEmployeeAttendance(@RequestParam Long employeeId){
        return new ResponseEntity<>(new EntityResponse(iEmployeeAttendanceService.GetEmployeeAttendance(employeeId),0), HttpStatus.OK);

    }
}
