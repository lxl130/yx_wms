package com.yx.wms_web.Base;

import com.yx.cache.SessionFactory;
import com.yx.model.Base.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    private SessionFactory sessionFactory;

    //获取Cookie中的UserId
    protected String GetUserId() {
        return sessionFactory.Get(Const.USER_ID);
    }

    //获取Cookie中的GroupId
    protected String GetGroupId() {
        return sessionFactory.Get(Const.GROUP_ID);
    }
}
