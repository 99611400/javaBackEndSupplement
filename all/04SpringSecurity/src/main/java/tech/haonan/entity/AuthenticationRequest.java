package tech.haonan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    // 认证请求的参数
    private String username;
    private String password;
}
