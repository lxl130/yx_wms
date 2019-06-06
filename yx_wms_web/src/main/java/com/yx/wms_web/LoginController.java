package com.yx.wms_web;

import com.yx.cache.SessionFactory;
import com.yx.model.Base.Const;
import com.yx.model.User.User;
import com.yx.wms_service.LoginService;
import com.yx.wms_web.Base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yx.utility.MD5Util;

import java.util.List;

@Controller
@RequestMapping(value={"/Login"})
public class LoginController extends BaseController {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value={"","/"})
    public String Login() {
        //清空cookies
        System.out.print("pre-"+GetUserId());
        sessionFactory.Delete(Const.USER_ID);
        System.out.print("post-"+GetUserId());

        return "Login/index";
    }

    @ResponseBody
    @RequestMapping(value = "/OnLogin", method = RequestMethod.POST)
    public String CheckLogin(User user) {
        user.setPassWord(MD5Util.encrypt(user.getPassWord()));
        User loginUser = loginService.CheckLogin(user.getPhone(), user.getPassWord());

        //登录失败
        if (loginUser == null || StringUtils.isBlank(loginUser.getUserId())) return "用户名或密码错误";

        //登录成功，并记录Cookie值
        sessionFactory.Set(Const.USER_ID, loginUser.getUserId());
        sessionFactory.Set(Const.GROUP_ID, loginUser.getGroupId());

        return "";
    }
}
