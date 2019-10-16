package com.example.annotation.aspect;

import com.example.annotation.interfaceclass.ParamValidate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Method;
import java.net.BindException;
import java.util.List;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 19:08
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class ExceptionAspect {
    @Before("@annotation(com.example.annotation.interfaceclass.ParamValidate)")
    public void before(JoinPoint joinPoint) throws Exception{
        doBefore(joinPoint);
    }


    private void doBefore(JoinPoint joinPoint) throws Exception{
        if (getParamValidate(joinPoint) == null) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }
        formateException(args);
    }

    private ParamValidate getParamValidate(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(ParamValidate.class);
    }

    private void formateException(Object[] args) throws Exception {
        for (Object arg: args
             ) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult != null && bindingResult.getErrorCount() > 0){
                    List<ObjectError> list = bindingResult.getAllErrors();
                    String errorMsg = "";
                    for (ObjectError objectError:list
                         ) {
                        if (objectError instanceof FieldError) {
                            FieldError fieldError = (FieldError) objectError;
                            errorMsg = String.format("%s:%s", fieldError.getField(), objectError.getDefaultMessage());
                        } else {
                            errorMsg = String.format("%s:%s", objectError.getCode(), objectError.getDefaultMessage());
                        }
                        log.error(errorMsg);
                        throw new Exception(errorMsg);
                    }

                }
            }
        }
    }

}
