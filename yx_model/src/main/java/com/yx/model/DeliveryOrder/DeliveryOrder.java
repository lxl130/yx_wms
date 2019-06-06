package com.yx.model.DeliveryOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DeliveryOrder {

    //主键
    @Getter @Setter
    private String deliveryOrderId;

    //集团编号（客户的供应商）
    @Getter @Setter
    private String groupId;

    //仓库所属公司（子公司）
    @Getter @Setter
    private String companyId;

    //商品所属客户
    @Getter @Setter
    private String customerId;

    //仓库编号
    @Getter @Setter
    private String stockId;

    //物流公司编号
    @Getter @Setter
    private String expressId;

    //出库单号/销售单号
    @Getter @Setter
    private String deliveryOrderNo;

    //原出库单号(ERP分配)
    @Getter @Setter
    private String erpOrderNo;

    //原出库单号(WMS分配)
    @Getter @Setter
    private String wmsOrderNo;

    //出库单类型
    @Getter @Setter
    private String deliveryOrderType;

    //出库单状态
    @Getter @Setter
    private String deliveryOrderStatus;

    //订单标记
    @Getter @Setter
    private String deliveryOrderFlag;

    //(0.手动导入1.系统推送)
    @Getter @Setter
    private String createType;

    //订单来源平台编码
    @Getter @Setter
    private String platformId;

    //订单来源平台名称
    @Getter @Setter
    private String platformName;

    //订单来源店铺编号
    @Getter @Setter
    private String shopId;

    //订单日期
    @Getter @Setter
    private String orderTime;

    //下单时间
    @Getter @Setter
    private String buyTime;

    //订单支付时间
    @Getter @Setter
    private String payTime;

    //支付平台交易号
    @Getter @Setter
    private String payNo;

    //服务编码
    @Getter @Setter
    private String serviceCode;

    //卖家名称
    @Getter @Setter
    private String sellerNick;

    //买家昵称
    @Getter @Setter
    private String buyerNick;

    //应发总数量
    @Getter @Setter
    private String planTotalQty;

    //实发总数量
    @Getter @Setter
    private String sentTotalQty;

    //订单总金额
    @Getter @Setter
    private String totalAmount;

    //商品总金额
    @Getter @Setter
    private String itemAmount;

    //订单折扣金额
    @Getter @Setter
    private String discountAmount;

    //快递费用
    @Getter @Setter
    private String freight;

    //总重量
    @Getter @Setter
    private String sumWeight;

    //应收金额
    @Getter @Setter
    private String arAmount;

    //已收金额
    @Getter @Setter
    private String gotAmount;

    //COD服务费
    @Getter @Setter
    private String serviceFee;

    //运单号
    @Getter @Setter
    private String expressNo;

    //快递区域编码, 大头笔信息
    @Getter @Setter
    private String logisticsAreaCode;

    //投递时延要求
    @Getter @Setter
    private String scheduleType;

    //要求送达日期
    @Getter @Setter
    private String scheduleDay;

    //投递时间范围要求 (开始时间)
    @Getter @Setter
    private String scheduleStartTime;

    //投递时间范围要求 (结束时间)
    @Getter @Setter
    private String scheduleEndTime;

    //收件人公司名称
    @Getter @Setter
    private String receiverCompany;

    //收件人姓名
    @Getter @Setter
    private String receiverName;

    //收件人邮编
    @Getter @Setter
    private String receiverZipCode;

    //收件人固定电话
    @Getter @Setter
    private String receiverTel;

    //收件人移动电话
    @Getter @Setter
    private String receiverMobile;

    //收件人证件类型
    @Getter @Setter
    private String receiverIdType;

    //收件人证件号码
    @Getter @Setter
    private String receiverIdNumber;

    //收件人电子邮箱
    @Getter @Setter
    private String receiverEmail;

    //收件人国家二字码
    @Getter @Setter
    private String receiverCountryCode;

    //收件人省份编号
    @Getter @Setter
    private String receiverProvinceId;

    //收件人省份
    @Getter @Setter
    private String receiverProvince;

    //收件人城市编号
    @Getter @Setter
    private String receiverCityId;

    //收件人城市
    @Getter @Setter
    private String receiverCity;

    //收件人区域编号
    @Getter @Setter
    private String receiverAreaId;

    //收件人区域
    @Getter @Setter
    private String receiverArea;

    //收件人村镇
    @Getter @Setter
    private String receiverTown;

    //收件人详细地址
    @Getter @Setter
    private String receiverAddress;

    //是否紧急, Y/N, 默认为N
    @Getter @Setter
    private String isUrgency;

    //订单是否挂起
    @Getter @Setter
    private String isSuspend;

    //订单是否取消
    @Getter @Setter
    private String isCancel;

    //订单是否预售
    @Getter @Setter
    private String isBooking;

    //买家留言
    @Getter @Setter
    private String buyerMessage;

    //卖家留言
    @Getter @Setter
    private String sellerMessage;

    //挂起原因
    @Getter @Setter
    private String suspendRemark;

    //取消原因
    @Getter @Setter
    private String cancelRemark;

    //订单备注
    @Getter @Setter
    private String orderRemark;

    //发货时间
    @Getter @Setter
    private String deliveryTime;

    //新增到系统的创建时间
    @Getter @Setter
    private String createTime;

    //新增到系统的创建人
    @Getter @Setter
    private String createUser;

    //新增到系统的修改人
    @Getter @Setter
    private String updateUser;

    //修改时间
    @Getter @Setter
    private String updateTime;

    //扩展字段1
    @Getter @Setter
    private String extendProp1;

    //扩展字段2
    @Getter @Setter
    private String extendProp2;

    //扩展字段3
    @Getter @Setter
    private String extendProp3;

    //发货单明细
    @Getter @Setter
    private List<DeliveryOrderItem> items;

    //公司名称
    @Getter @Setter
    private String companyName;

    //客户名称
    @Getter @Setter
    private String customerName;

    //发货仓库
    @Getter @Setter
    private String stockName;

    //承运商
    @Getter @Setter
    private String expressName;

    private String position;

    public String getPosition() { return receiverProvince+receiverCity+receiverArea; }

    private String receiverInfo;

    public String getReceiverInfo() { return receiverName+receiverMobile; }
}
