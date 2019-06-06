package com.yx.model.Company;

import com.yx.model.Base.comm;
import lombok.Getter;
import lombok.Setter;

public class Customer {
    //表属性
    @Getter @Setter
    private String customerId;

    @Getter @Setter
    private String customerCode;

    @Getter @Setter
    private String customerName;

    @Getter @Setter
    private String customerFullName;

    @Getter @Setter
    private String groupId;

    @Getter @Setter
    private String createUser;

    @Getter @Setter
    private String updateUser;

    @Getter @Setter
    private String createTime;

    @Getter @Setter
    private String updateTime;

    @Getter @Setter
    private String validate;

    private String validateName;

    //扩展属性
    @Getter @Setter
    private String groupName;

    @Getter @Setter
    private String companyNames;

    @Getter @Setter
    private String createUserName;

    public String getValidateName() {
        return comm.DataValidate.get(validate);
    }
}
