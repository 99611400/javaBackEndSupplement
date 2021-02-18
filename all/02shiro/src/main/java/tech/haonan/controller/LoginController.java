package tech.haonan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/user/index")
    public String index(){
        return "我是主页";
    }

    @RequestMapping("/user/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出成功";
    }

    @RequestMapping("/user/login")
    public String login(String username,String password){
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "登录成功";
        } catch (UnsupportedTokenException e) {
            System.out.println("用户名错误");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("其他错误");
            e.printStackTrace();
        }
        return "登录失败";

    }
    @RequestMapping("/user/test1")
    public String test1(){
        return "test1";
    }

    @RequestMapping("/user/test2")
    public String test2(){
        return "test2";
    }

    @RequestMapping("/user/test3")
    public String test3(){
        return "test3";
    }

}
