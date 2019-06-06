package com.yx.model.Stock;

import lombok.Getter;
import lombok.Setter;

public class StockCustomerContact {
    //表属性
    @Getter @Setter
    private String stockCustomerId;

    @Getter @Setter
    private String companyId;

    @Getter @Setter
    private String stockId;

    @Getter @Setter
    private String customerId;

    @Getter @Setter
    private String createUser;

    @Getter @Setter
    private String createTime;

    //扩展属性
    @Getter @Setter
    private String stockName;

    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private String customerName;

    @Getter @Setter
    private String createUserName;

    @Getter @Setter
    private Boolean isChecked;
}
