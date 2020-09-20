package com.study.provider.controller;

import com.study.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yanghao
 * @create 2020-09-06 11:12
 * @Description:
 */
@Controller
public class RegisterController {

    @PostMapping("/register")
    public String register(User user){
        return "redirect:http://provider/loginPage?username=" + user.getUsername();
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username){
        return "loginPage: " + username;
    }
}
