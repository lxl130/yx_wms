package com.yx.wms_dao.User;

import com.yx.model.User.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {

    //查询用户列表
    List<User> GetUserList(HashMap<String, String> filter);

    //新增用户
    void InsertUser(User user);

    //修改用户
    void UpdateUser(User user);

    //删除用户
    void DeleteUser(Long id);

    //登录查询
    User CheckLogin(@Param("phone") String phone, @Param("passWord") String passWord);
}
