package com.yx.wms_web.GlobalException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * 通过使用@ControllerAdvice定义统一的异常处理类，可以不用在每个Controller中逐个定义异常处理方式
 * @ExceptionHandler 用来定义函数针对的异常类型，controller通过抛出的异常类型匹配
 * 最后将Exception对象和请求URL映射到 resource/templates/error.html中
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @RestControllerAdvice
    public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(value = Exception.class)
        public String handleDefaultException(Exception ex, WebRequest request) {
            return "{}";
        }

    }


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //默认异常处理页面
    public static final String DEFAUL_ERROR_VIEW = "Shared/Error";


    /**
     * 默认异常处理方法,返回异常请求路径和异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaulErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws  Exception{

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e);                   //异常信息
        mav.addObject("url","请求路径：" + request.getRequestURI());   //异常请求路径
        mav.setViewName(DEFAUL_ERROR_VIEW);                          //返回异常处理页面
        return mav;
    }

    /**
     * @ExceptionHandler 匹配抛出的异常类型
     * ErrorDTO<String> 为自定义的一个数据封装类，用于返回的json数据
     * 如果返回的是json格式，需要加上@ResponsBody
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorDTO<String> jsonErrorHandler(HttpServletRequest request, MyException e) throws Exception{
        ErrorDTO<String> error = new ErrorDTO<String>();
        error.setCode(ErrorDTO.ERROR);
        error.setMessage(e.getMessage());
        error.setUrl(request.getRequestURI());
        error.setData("未知错误");
        return error;
    }


}
