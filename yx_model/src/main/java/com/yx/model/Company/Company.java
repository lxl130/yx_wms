package com.yx.model.Company;

import com.yx.model.Base.comm;
import lombok.Getter;
import lombok.Setter;

public class Company {
    //表属性
    @Getter @Setter
    private String companyId;

    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private String companyCode;

    @Getter @Setter
    private String companyFullName;

    @Getter @Setter
    private String parentId;

    @Getter @Setter
    private String groupId;

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

    private String validateName;

    //扩展属性
    @Getter @Setter
    private String groupName;

    public String getValidateName() {
        return comm.DataValidate.get(validate);
    }
}
