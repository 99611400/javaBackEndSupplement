package tech.haonan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.haonan.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController

public class TestController {
    // 只有携带token才可以访问 否则不行
    @RequestMapping("/test/hello")
    public String test() {
        return "hello, world";
    }

    // 公共资源 允许随便访问
    @RequestMapping("/user/loginTest")
    public String loginTest(String username, HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("username"));
        request.getSession().setAttribute("username", username);
        return username + " login" + JWTUtils.getToken(new HashMap<>());
    }
}
