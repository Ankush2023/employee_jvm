package com.epms.employee.service.serviceimpl;

import com.epms.employee.model.Employee;
import com.epms.employee.repository.EmployeeRepository;
import com.epms.employee.service.IEmployeeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeAccessService implements IEmployeeAccessService, UserDetailsService {
 @Autowired
 private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepository.findByEmailIgnoreCaseAndIsDeleted(s, false).map(employee -> new User(employee.getEmail(), employee.getPassword(), getAuthority(employee)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + s));
    }


    @Override
    public Employee findByEmail(String username) {
        return employeeRepository.findByEmailIgnoreCaseAndIsDeleted(username, false).orElse(new Employee());
    }


    private Collection<? extends GrantedAuthority> getAuthority(Employee employee) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }
}
