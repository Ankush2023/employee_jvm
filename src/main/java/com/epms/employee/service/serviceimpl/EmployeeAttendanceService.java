package com.epms.employee.service.serviceimpl;

import com.epms.employee.exception.EmployeeNotFoundException;
import com.epms.employee.model.Employee;
import com.epms.employee.model.EmployeeAttendance;
import com.epms.employee.model.request.EmployeeAttendanceRequest;
import com.epms.employee.repository.EmployeeAttendanceRepository;
import com.epms.employee.repository.EmployeeRepository;
import com.epms.employee.service.IEmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmployeeAttendanceService implements IEmployeeAttendanceService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Override
    public Object saveOrUpdateEmployeeAttendance(EmployeeAttendanceRequest employeeAttendanceRequest) {
        Employee employee=employeeRepository.findById(employeeAttendanceRequest.getEmployeeId()).orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        return employeeAttendanceRepository.findByEmployeeIdAndDate(employee.getId(),LocalDate.parse(employeeAttendanceRequest.getDate())).map(employeeAttendance -> {
            employeeAttendance.setDate(LocalDate.parse(employeeAttendanceRequest.getDate()));
            employeeAttendance.setEmployee(employee);
            employeeAttendance.setEndTime(LocalTime.parse(employeeAttendanceRequest.getEndTime()));
            employeeAttendance.setStartTime(LocalTime.parse(employeeAttendanceRequest.getStartTime()));
            employeeAttendance.setIsLeave(false);
            Duration duration= Duration.between(LocalTime.parse(employeeAttendanceRequest.getStartTime()),LocalTime.parse(employeeAttendanceRequest.getEndTime()));
            Long hours=duration.getSeconds();
            if(hours<60){
              employeeAttendance.setTotalHours(hours+" seconds");
            }else if(hours>=60 &&hours<3600){
                employeeAttendance.setTotalHours((hours/60) +" minutes,"+(hours%60)+" seconds");
            }else if(hours>=3600){
                employeeAttendance.setTotalHours((hours/3600) +" hours,"+(hours%3600)+" minutes,"+((hours%3600)%60)+" seconds");
            }
            employeeAttendanceRepository.save(employeeAttendance);
            return "updated successfully";
        }).orElseGet(() -> {
            EmployeeAttendance employeeAttendance=new EmployeeAttendance();
            employeeAttendance.setDate(LocalDate.parse(employeeAttendanceRequest.getDate()));
            employeeAttendance.setEmployee(employee);
            employeeAttendance.setEndTime(LocalTime.parse(employeeAttendanceRequest.getEndTime()));
            employeeAttendance.setStartTime(LocalTime.parse(employeeAttendanceRequest.getStartTime()));
            employeeAttendance.setIsLeave(false);
            Duration duration= Duration.between(LocalTime.parse(employeeAttendanceRequest.getStartTime()),LocalTime.parse(employeeAttendanceRequest.getEndTime()));
            Long hours=duration.getSeconds();
            if(hours<60){
                employeeAttendance.setTotalHours(hours+" seconds");
            }else if(hours>=60 &&hours<3600){
                employeeAttendance.setTotalHours((hours/60) +" minutes,"+(hours%60)+" seconds");
            }else if(hours>=3600){
                employeeAttendance.setTotalHours((hours/3600) +" hours,"+(hours%3600)+" minutes,"+((hours%3600)%60)+" seconds");
            }
            employeeAttendanceRepository.save(employeeAttendance);
            return "saved successfully";
        });
    }

    @Override
    public Object GetEmployeeAttendance(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        return employeeAttendanceRepository.findAllByEmployeeId(employee.getId());
    }
}
