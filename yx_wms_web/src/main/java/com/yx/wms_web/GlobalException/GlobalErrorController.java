package com.yx.wms_web.GlobalException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GlobalErrorController   implements ErrorController {
        private static final String ERROR_PATH = "/error";
        @RequestMapping(value=ERROR_PATH)
        public String handleError(HttpServletRequest request, HttpServletResponse response){
            return "Shared/Error";
        }
        @Override
        public String getErrorPath() {
            return ERROR_PATH;
        }
    }
