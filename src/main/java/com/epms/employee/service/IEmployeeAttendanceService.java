package com.epms.employee.service;

import com.epms.employee.model.request.EmployeeAttendanceRequest;

public interface IEmployeeAttendanceService {
    Object saveOrUpdateEmployeeAttendance(EmployeeAttendanceRequest employeeAttendanceRequest);

    Object GetEmployeeAttendance(Long employeeId);
}
