package com.example.annotation.globalexception;

import com.example.annotation.exception.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 20:06
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo<Object> handleException(Exception e){
        String errorMsg = "";
        if (e instanceof NullPointerException) {
            errorMsg = "参数空指针异常";
        } else if (e instanceof HttpMessageNotReadableException) {
            errorMsg = "请求参数匹配有误" + e.getLocalizedMessage();
        } else {
            errorMsg = e.getMessage();
        }
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setResultCode("501");
        resultVo.setResultMsg(errorMsg);
        return resultVo;
    }
}
