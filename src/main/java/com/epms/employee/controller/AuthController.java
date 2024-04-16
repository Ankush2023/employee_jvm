package com.epms.employee.controller;

import com.epms.employee.config.TokenProvider;
import com.epms.employee.model.Employee;
import com.epms.employee.model.request.LoginRequest;
import com.epms.employee.model.response.EntityResponse;
import com.epms.employee.model.response.LoginResponse;
import com.epms.employee.service.IEmployeeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private IEmployeeAccessService employeeAccessService;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            final Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            final UserDetails candidateDetails = employeeAccessService.loadUserByUsername(loginRequest.getEmail());
            Employee employee = employeeAccessService.findByEmail(candidateDetails.getUsername());

            String token = "";
            try {
                token = jwtTokenUtil.generateToken(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setEmployeeId(employee.getId());
            loginResponse.setEmployeeName(employee.getEmployeeName());
            return new ResponseEntity<>(new EntityResponse(loginResponse, 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.UNAUTHORIZED);

        }
    }

    private Authentication authenticate(String email, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // throw new Exception("INVALID_CREDENTIALS", e);
            throw new Exception("Please Check Username and Password", e);
        } catch (Exception e) {
            throw new Exception("Please Check Username and Password", e);
        }
    }

}

