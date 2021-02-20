package tech.haonan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String sex;
    private Set<String> authorities;

}
