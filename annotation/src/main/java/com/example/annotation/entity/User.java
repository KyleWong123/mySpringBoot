package com.example.annotation.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 20:16
 * @Version 1.0
 */
@Data
public class User {
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "手机号bunengweikong")
    private String mobile;
}
