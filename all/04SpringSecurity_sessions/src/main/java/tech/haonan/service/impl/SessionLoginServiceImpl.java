package tech.haonan.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.haonan.entity.AuthenticationRequest;
import tech.haonan.entity.User;
import tech.haonan.service.SessionLoginService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SessionLoginServiceImpl implements SessionLoginService {

    @Override
    public User authentication(AuthenticationRequest authenticationRequest) {
        if (null == authenticationRequest ||
                StringUtils.isEmpty(authenticationRequest.getUsername()) ||
                StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或者密码为空");
        }
        User user = getUser(authenticationRequest.getUsername());
        if (null == user){
            throw new RuntimeException("查询不到该用户");
        }
        if (!authenticationRequest.getPassword().equals(user.getPassword())){
            throw new RuntimeException("账号或者密码错误");
        }
        return user;
    }

    public User getUser(String username){
        return userMap.get(username);
    }

    // 这里使用了假数据 正常流程应该是查询数据库 这里图方便直接没查
    private final Map<String,User> userMap = new HashMap<>();
    {
        // admin只有访问a1的权限 也就是 资源1 同理 wangwu只有资源2的访问权限
        Set<String> set1 = new HashSet<>();
        set1.add("a1");
        Set<String> set2 = new HashSet<>();
        set2.add("a2");

        userMap.put("admin",new User("admin","123","男",set1));
        userMap.put("wangwu",new User("wangwu","1456","女",set2));
    }
}
