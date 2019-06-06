package com.yx.wms_web.Demos;

import com.yx.wms_service.Demos.RedisDemoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Desc: Redis测试
 */

@RestController
@RequestMapping(value={"/Demos/Redis"})
public class RedisDemoController {
    @Autowired
    private RedisDemoService redisDemoService;

    /**
     * Created: LiuWenLong
     * Date: 2019-3-20
     * Desc: 获取所有key
     * @return
     */
   @ResponseBody
   @RequestMapping(value = "/getAllKey", method = RequestMethod.POST)
   public String getAllKey(){
       Set<String> r= redisDemoService.getAllKey();
       return JSON.toJSONString(r);
   }
    /**
     * Created: LiuWenLong
     * Date: 2019-3-20
     * Desc: 通过key读取值
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByKey", method = RequestMethod.GET)
    public String getByKey(@RequestParam String key) {
        return redisDemoService.get(key);
    }
    /**
     * Created: LiuWenLong
     * Date: 2019-3-20
     * Desc: 通过 {key:value} 方式设置值
     * @param jsonParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/storeKeys", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String storeKeys(@RequestBody JSONObject jsonParam) {
        for (Map.Entry<String, Object> entry : jsonParam.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            redisDemoService.set(entry.getKey(),entry.getValue());
        }
        return jsonParam.toJSONString();
    }

}
