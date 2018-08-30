package cn.edu.scau.DataCollectionSystem.controller;

import cn.edu.scau.DataCollectionSystem.dto.response.BaseResponse;
import cn.edu.scau.DataCollectionSystem.dto.request.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${session.timeout}")
    private long sessionTimeout;

    @RequestMapping(value = "/login")
    public BaseResponse login(@RequestBody Login request) {

        UsernamePasswordToken token = new UsernamePasswordToken("", request.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return new BaseResponse().setCode(11);
        }

        Session session = subject.getSession();
        session.setTimeout(sessionTimeout);

        return new BaseResponse().setCode(10);
    }

    @RequestMapping(value = "/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
    }

}
