package tech.haonan.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import tech.haonan.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 获取请求头中的信息
        String token = request.getHeader("token");
        try {
            JWTUtils.verifyToken(token);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名");
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期");
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "算法不一致");
            e.printStackTrace();
        } catch (Exception e) {
            map.put("msg", "token无效");
        }
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
        return false;
    }
}
