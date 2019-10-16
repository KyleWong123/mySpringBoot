package com.example.annotation.Controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.annotation.entity.User;
import com.example.annotation.exception.ResultVo;
import com.example.annotation.interfaceclass.ParamValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 20:19
 * @Version 1.0
 */
@Slf4j
@ParamValidate
@RestController
@RequestMapping("/user")
public class UserController {
    @ParamValidate
    @RequestMapping("adduser")
    public String addUser(@RequestBody @Valid User user, BindingResult result) throws Exception {
        int i = 1/0;
        return "success";
    }
}
