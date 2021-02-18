package tech.haonan;

import static org.junit.Assert.assertTrue;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testJwtGenerate() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 10000); // 20小时之后

        HashMap<String, Object> map = new HashMap<>();
        String token = JWT.create()
                .withHeader(map)  // 这里可以省略
                .withClaim("userId", 19)  //
                .withClaim("username", "张三")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("abcdefg"));
        System.out.println(token);

        /*
         header eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
         {"typ":"JWT","alg":"HS256"}
         payload eyJleHAiOjE2MTM0MDYyNjAsInVzZXJJZCI6MTksInVzZXJuYW1lIjoi5byg5LiJIn0.
         {"exp":1613406260,"userId":19,"username":"张三"}
         signature BAG7w5HkUol1S89DUuqPlW5fbcWkGzYs8x6OPpWlZVU
         */
    }

    @Test
    public void testJwtParse() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!QWERTYU")).build();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTM0MDYyNjAsInVzZXJJZCI6MTksInVzZXJuYW1lIjoi5byg5LiJIn0.BAG7w5HkUol1S89DUuqPlW5fbcWkGzYs8x6OPpWlZVU";
        DecodedJWT verify = jwtVerifier.verify(token);
        System.out.println(verify.getClaim("userId"));
        System.out.println(verify.getExpiresAt());
        System.out.println(verify.getClaim("username"));
    }

    @Test
    public void getTime() {

        Date date = new Date(1613404189L * 1000);
        //System.out.println(date.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);

//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.SECOND, 10000); // 20小时之后
//        System.out.println(instance);
    }
}
