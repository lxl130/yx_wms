package com.yx.wms_web;

import com.yx.wms_web.Base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController extends BaseController {
    @RequestMapping(value={"", "/Index"})
    public String Index() {
        System.out.print(GetUserId());
        return "Home/index";
    }
}
