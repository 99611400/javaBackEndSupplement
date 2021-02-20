package tech.haonan.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tech.haonan.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验是否登录 而且鉴定用户是否有对应权限访问对应资源
        Object object = request.getSession().getAttribute("abc");
        if(object == null){
            // 没有登录
            writeContent(response, "请登录");
        }
        User user = (User) object;
        String requestUrl = request.getRequestURI();
        if(user.getAuthorities().contains("a1") && requestUrl.contains("resources1")){
            // 说明有资源1的访问权限
            return true;
        }
        if(user.getAuthorities().contains("a2") && requestUrl.contains("resources2")){
            // 说明有资源1的访问权限
            return true;
        }
        writeContent(response,"没有权限，拒绝访问");
        return false;
    }

    // response中返回对应提示
    private void writeContent(HttpServletResponse response, String s) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(s);
        writer.close();
    }
}
