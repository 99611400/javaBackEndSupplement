package tech.haonan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import tech.haonan.entity.User;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "测试controller", value = "测试哈哈哈")
public class TestController {
    @ApiOperation(value="测试方法", notes="我是一个测试方法 哈哈哈")
    @GetMapping("test/test")
    public String test(@ApiParam("我是测试参数")String abc){
        return "hello,swagger2" + abc;
    }
    @ApiOperation(value="测试方法2", notes="2")
    @PostMapping("user/testLogin")
    public String user(User user){
        return   user.getUsername();
    }

    @ApiOperation(value="测试方法3", notes="2")
    @PostMapping("user/test02")
    public String user(@RequestBody String body, HttpServletRequest request){
        return   body;
    }
}
