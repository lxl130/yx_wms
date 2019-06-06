package com.yx.model.Company;

import lombok.Getter;
import lombok.Setter;

public class CompanyContact {
    //表属性
    @Getter @Setter
    private String contactId;

    @Getter @Setter
    private String companyId;

    @Getter @Setter
    private String customerId;

    //扩展属性
    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private Boolean isChecked;
}
