package com.yx.wms_service.User;

import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.model.User.User;
import com.yx.wms_dao.User.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //获取用户列表
    public GridResponse<User> GetUserList(String groupId, GridRequest request) {
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        List<User> list=userMapper.GetUserList(filterList);
        return new GridResponse(list);
    }
}