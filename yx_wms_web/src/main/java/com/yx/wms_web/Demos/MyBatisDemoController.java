package com.yx.wms_web.Demos;

import com.alibaba.fastjson.JSON;
import com.yx.wms_service.Demos.MyBatisDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Desc: MyBatis测试
 */

@RestController
@RequestMapping(value={"/Demos/MyBatis"})
public class MyBatisDemoController {

    @Autowired
    private MyBatisDemoService myBatisDemoService;

}
