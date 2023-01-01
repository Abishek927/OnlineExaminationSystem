package com.online.exam.jwthelper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtRequest {
    private String username;
    private String password;

}
