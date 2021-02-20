package tech.haonan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.haonan.entity.AuthenticationRequest;
import tech.haonan.entity.User;
import tech.haonan.service.SessionLoginService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("test")
public class SessionLoginController {
    @Autowired
    SessionLoginService sessionLoginService;


    /**
     *  http://localhost:8080/test/sessionLogin?username=admin&password=123
     * 登录接口 如果登录成功 会建立一个session
     * @param authenticationRequest  包含username与password
     * @param session
     * @return
     */

    @RequestMapping("sessionLogin")
    public String sessionLogin(AuthenticationRequest authenticationRequest, HttpSession session){
        User user = sessionLoginService.authentication(authenticationRequest);
        //  存储一个session
        session.setAttribute("abc",user);
        return user.getUsername() + "登录成功";
    }


    /**
     * 测试session登录方法 如果已经登录过了 就会显示已登录 否则显示未登录
     * @param session
     * @return
     */
    @RequestMapping("abc")
    public String sessionLogin(HttpSession session){
        Object object = session.getAttribute("abc");
        String str;
        if(object ==null){
            str = "未登录";
        }else {
            User user = (User) object;
            str = user.getUsername() + "已经登录";
        }
        return str;
    }
}
