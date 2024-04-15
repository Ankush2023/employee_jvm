package com.epms.employee.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long employeeId;
    private String employeeName;
}
