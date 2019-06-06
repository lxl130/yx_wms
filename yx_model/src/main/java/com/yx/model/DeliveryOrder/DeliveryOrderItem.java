package com.yx.model.DeliveryOrder;

import lombok.Getter;
import lombok.Setter;

public class DeliveryOrderItem {
    //主键
    @Getter @Setter
    private String deliveryOrderItemId;

    //发货单ID
    @Getter @Setter
    private String deliveryOrderId;

    //行号
    @Getter @Setter
    private String orderLineNo;

    //商品ID
    @Getter @Setter
    private String skuId;

    //计划数量
    @Getter @Setter
    private String planQty;

    //实发数量
    @Getter @Setter
    private String actualQty;

    //零售价
    @Getter @Setter
    private String retailPrice;

    //成交价
    @Getter @Setter
    private String actualPrice;

    //商品毛重
    @Getter @Setter
    private String grossWeight;

    //备注
    @Getter @Setter
    private String comments;
}
