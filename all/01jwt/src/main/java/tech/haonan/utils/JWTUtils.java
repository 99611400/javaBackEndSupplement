package tech.haonan.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String SIGNATURE = "abcdefg";

    public static String getToken(Map<String, String> keyMap) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //7 天之后

        /*
        JWTCreator.Builder builder = JWT.create();
        keyMap.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        */
        // 上方的写法是完整写法  下方是lambda写法
        JWTCreator.Builder builder = JWT.create();
        keyMap.forEach(builder::withClaim);
        builder.withExpiresAt(instance.getTime()); //指定过期时间
        return builder.sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static DecodedJWT verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }

}

class main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "123");
        map.put("username", "abcd");
        String token = JWTUtils.getToken(map);
        System.out.println(token);


        DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
        System.out.println(decodedJWT.getHeader());
        System.out.println(decodedJWT.getPayload());
        System.out.println(decodedJWT.getSignature());
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        String format = dateFormat.format(decodedJWT.getExpiresAt());
        System.out.println(format);
    }
}