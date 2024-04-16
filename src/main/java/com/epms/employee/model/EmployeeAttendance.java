package com.epms.employee.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "epms_employee_attendance")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_attendance_id")
    private Long employeeAttendanceId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "total_hours")
    private String totalHours;

    @Column(name = "is_leave")
    private Boolean isLeave;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
