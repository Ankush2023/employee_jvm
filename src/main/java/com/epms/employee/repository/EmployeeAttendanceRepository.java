package com.epms.employee.repository;

import com.epms.employee.model.Employee;
import com.epms.employee.model.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance,Long> {

    Optional<EmployeeAttendance> findByEmployeeIdAndDate(Long employeeId, LocalDate parse);

    List<EmployeeAttendance> findAllByEmployeeId(Long employeeId);
}
