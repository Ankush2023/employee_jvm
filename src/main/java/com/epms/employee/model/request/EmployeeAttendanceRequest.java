package com.epms.employee.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EmployeeAttendanceRequest {
    private Long employeeAttendanceId;
    private String date;
    private String startTime;
    private String endTime;
    private Long employeeId;
}
