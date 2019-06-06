package com.yx.wms_web.Demos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yx.wms_web.GlobalException.MyException;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Desc: 请求测试
 */

@RestController
@RequestMapping(value={"/Demos/Request"})
public class RequestDemoController {
    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 接收post请求JSON数据
     * @param jsonParam 这个是阿里的 fastjson对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postJSON", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());
        return jsonParam.toJSONString();
    }


    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 接收get请求URL传参
     * @param id,name,age
     * @return String
     */
    @RequestMapping(value="/getParam",method= RequestMethod.GET)
    public String getParam(@RequestParam Integer id,@RequestParam String name,@RequestParam Integer age) {
        return "id:"+id+ " name:"+name+" age:"+age;
    }


    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 接收get请求URL传参,且允许为空时使用默认值
     * @param id
     * @return String
     */
    @RequestMapping(value="/getParam2",method= RequestMethod.GET)
    //required=false 表示url中可以无id参数，此时就使用默认参数
    public String getParam2(@RequestParam(value="id",required = false,defaultValue = "1") Integer id){
        return "id:"+id;
    }


    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 接收post请求的文本数据流
     * @param id
     * @return String
     */
    @RequestMapping(value="/postString",method= RequestMethod.POST)
    public String postString(HttpServletRequest request) {
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
//            System.out.println(sb.toString());
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 上传文件
     * @param file
     * @param redirectAttributes
     * @return String
     */
    @RequestMapping(value="/uploadFile",method= RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,    RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");//传递重定向参数
//            return "redirect:uploadStatus";//redirect 重定向
            return "fail";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./" + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "redirect:/uploadStatus";
        return file.getOriginalFilename();
    }


    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: 上传多文件
     * @param file,request
     * @return String
     */
    @RequestMapping(value="/uploadMultipartFile",method= RequestMethod.POST)
    public String uploadMultipartFile(@RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request) {
        List<String> ls=new ArrayList<String>();
        for (MultipartFile file :files             ) {
            ls.add(file.getOriginalFilename());
        }
        return  JSON.toJSONString(ls);
    }



    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: application/x-www-form-urlencoded 表单提交
     * @param requestDemoModel
     * @return String
     */
    @PostMapping("/postForm")
    public String postForm(@ModelAttribute RequestDemoModel requestDemoModel) {
        return JSON.toJSONString(requestDemoModel);
    }

    /**
     * Created: LiuWenLong
     * Date: 2019-3-19
     * Desc: multipart/form-data 提交
     * @param map
     * @return String
     */
    @PostMapping("/postFormData")
    public String postFormData(@RequestParam Map<String,Object> map) {
//  public String postFormData(@ModelAttribute RequestDemoModel map) {
        return JSON.toJSONString(map);
    }

    @PostMapping("/500Test")
    public String postFormData(@ModelAttribute RequestDemoModel map) {
        int a=5/0;
        return JSON.toJSONString(map);
    }
    /**
     * 异常错误处理返回错误页面
     */
    @RequestMapping("/Exception")
    public String testErrorReturn1() throws Exception{
        throw new Exception("请求出错!");
    }

    /**
     * 异常错误处理返回json格式
     * 根据抛出的异常类型匹配到对应的异常处理方法
     */
    @RequestMapping("/returnJson")
    public String testErrorReturn2() throws MyException {
        throw new MyException("请求出错2");
    }

    @RequestMapping("/mmm")
    public String index(ModelMap map){
        map.addAttribute("host","http://baidu.com");
        return "index";
    }
}
