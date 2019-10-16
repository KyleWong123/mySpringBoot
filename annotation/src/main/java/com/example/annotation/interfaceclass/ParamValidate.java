package com.example.annotation.interfaceclass;

import java.lang.annotation.*;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 14:33
 * @Version 1.0
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidate {
    String value() default "";
}
