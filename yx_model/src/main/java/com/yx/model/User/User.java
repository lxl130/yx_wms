package com.yx.model.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class User {

    //表属性
    @Getter @Setter
    private String userId;

    @Getter @Setter
    private String userCode;

    @Getter @Setter
    private String realName;

    @Getter @Setter
    private String passWord;

    @Getter @Setter
    private String userType;

    @Getter @Setter
    private String groupId;

    @Getter @Setter
    private String companyId;

    @Getter @Setter
    private String deptId;

    @Getter @Setter
    private String jobId;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String telPhone;

    @Getter @Setter
    private String PIN;

    @Getter @Setter
    private String sex;

    @Getter @Setter
    private String employType;

    @Getter @Setter
    private String createUser;

    @Getter @Setter
    private String createTime;

    @Getter @Setter
    private String updateUser;

    @Getter @Setter
    private String updateTime;

    @Getter @Setter
    private String validate;

    //扩展属性

    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private String groupName;

    @Getter @Setter
    private String deptName;

    @Getter @Setter
    private String jobName;

    @Getter @Setter
    public String NewPwd;//新的密码

    @Getter @Setter
    public String SurePwd;//确认密码

    @Getter @Setter
    public String createUserName ;//创建人姓名

    @Getter @Setter
    public String userTypeName ;//用户类型

    @Getter @Setter
    public String driverLicenseNo ;//驾驶证号码

    @Getter @Setter
    public String driverLicenseType ;//驾驶证类型（C1/B1/A1）

    @Getter @Setter
    public String driverLicenseDueDate ;//驾驶证到期日期

    @Getter @Setter
    public String jobGradeName;//部门级别

    @Getter @Setter
    public String sexName;//男女

    @Getter @Setter
    public String userValidateName;//临时工，转正，离职

    @Getter @Setter
    public String directlyLeader ;//直属领导

    @Getter @Setter
    public String orgUnitId;//oa使用

    @Getter @Setter
    public List<String> PdaUserMenu;
}
