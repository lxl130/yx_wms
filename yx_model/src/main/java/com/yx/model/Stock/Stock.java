package com.yx.model.Stock;

import com.yx.model.Base.comm;
import lombok.Getter;
import lombok.Setter;

public class Stock {
    //表属性

    @Getter @Setter
    private String stockId;

    @Getter @Setter
    private String stockName;

    @Getter @Setter
    private String stockCode;

    @Getter @Setter
    private int stockType;

    @Getter @Setter
    private String groupId;

    @Getter @Setter
    private String companyId;

    @Getter @Setter
    private String customerCount;

    @Getter @Setter
    private String provinceId;

    @Getter @Setter
    private String province;

    @Getter @Setter
    private String cityId;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String areaId;

    @Getter @Setter
    private String area;

    @Getter @Setter
    private String detailAddress;

    @Getter @Setter
    private String zipCode;

    @Getter @Setter
    private String senderName;

    @Getter @Setter
    private String senderTelephone;

    @Getter @Setter
    private String senderMobPhone;

    @Getter @Setter
    private String senderEmail;

    @Getter @Setter
    private String createTime;

    @Getter @Setter
    private String createUser;

    @Getter @Setter
    private String updateTime;

    @Getter @Setter
    private String updateUser;

    @Getter @Setter
    private String validate;

    //扩展属性
    @Getter @Setter
    private String groupName;

    @Getter @Setter
    private String companyName;

    @Getter @Setter
    private String createUserName;

    private String position;

    public String getPosition() { return province+city+area; }

    private String senderInfo;

    public String getSenderInfo() { return senderName+senderMobPhone; }

    private String stockTypeName;

    public String getStockTypeName() {
        return comm.StockType.get(stockType);
    }

    private String validateName;

    public String getValidateName() {
        return comm.DataValidate.get(validate);
    }
}
