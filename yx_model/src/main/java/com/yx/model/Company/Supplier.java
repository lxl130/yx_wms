package com.yx.model.Company;

import com.yx.model.Base.comm;
import lombok.Getter;
import lombok.Setter;

public class Supplier {
    //表属性
    @Getter @Setter
    private String supplierId;

    @Getter @Setter
    private String supplierName;

    @Getter @Setter
    private String customerId;

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
    private String customerName;

    @Getter @Setter
    private String createUserName;

    public String getValidateName() {
        return comm.DataValidate.get(validate);
    }
}
