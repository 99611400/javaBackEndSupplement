package tech.haonan.service;

import tech.haonan.entity.AuthenticationRequest;
import tech.haonan.entity.User;

public interface SessionLoginService {
    User authentication(AuthenticationRequest authenticationRequest);
}
