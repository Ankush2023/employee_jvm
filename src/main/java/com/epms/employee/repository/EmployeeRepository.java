package com.epms.employee.repository;

import com.epms.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmailIgnoreCaseAndIsDeleted(String s, boolean b);
}
