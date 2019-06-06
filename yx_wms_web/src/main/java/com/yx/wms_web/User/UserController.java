package com.yx.wms_web.User;

import com.yx.model.User.User;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.wms_service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.yx.wms_web.Base.BaseController;

@Controller
@RequestMapping(value={"/User"})
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value={"","/"})
    public String User() {
        return "User/index";
    }

    @ResponseBody
    @RequestMapping(value ="/GetUserList",method = RequestMethod.POST)
    public GridResponse<User> GetUserList(@RequestBody GridRequest request) {
        return  userService.GetUserList(GetGroupId(), request);

    }

}
