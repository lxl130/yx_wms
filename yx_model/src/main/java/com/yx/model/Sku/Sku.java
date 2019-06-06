package com.yx.model.Sku;

import com.yx.model.Base.comm;
import lombok.Getter;
import lombok.Setter;

public class Sku {

    //主键
    @Getter @Setter
    private String skuId;

    //商品所属客户
    @Getter @Setter
    private String customerId;

    //集团ID
    @Getter @Setter
    private String groupId;

    //条形码,可多个，用分号（;）隔开
    @Getter @Setter
    private String skuNo;

    //商品名称
    @Getter @Setter
    private String skuName;

    //商品类型 (ZC=正常商品,FX=分销商品,ZH=组合商品,ZP=赠品,BC=包材,HC=耗材,FL=辅料,XN=虚拟品,FS=附属品,CC=残次品,OTHER=其它)
    @Getter @Setter
    private String skuType = "ZC";

    //货号
    @Getter @Setter
    private String goodsNo = skuNo;

    //供应商名称
    @Getter @Setter
    private String supplierId = "";

    //商品简称
    @Getter @Setter
    private String shortName = skuName;

    //英文名
    @Getter @Setter
    private String englishName = "";

    //商品属性 (如红色, XXL)
    @Getter @Setter
    private String skuProperty = "";

    //商品计量单位
    @Getter @Setter
    private String stockUnit = "";

    //长 (厘米)
    @Getter @Setter
    private double length;

    //宽 (厘米)
    @Getter @Setter
    private double width;

    //高 (厘米)
    @Getter @Setter
    private double height;

    //体积 (升)
    @Getter @Setter
    private double volume;

    //毛重 (千克)
    @Getter @Setter
    private double grossWeight;

    //净重 (千克)
    @Getter @Setter
    private double netWeight;

    //颜色
    @Getter @Setter
    private String color = "";

    //尺寸
    @Getter @Setter
    private String size = "";

    //渠道中的商品标题
    @Getter @Setter
    private String title = "";

    //商品类别ID
    @Getter @Setter
    private String categoryId = "";

    //商品类别名称
    @Getter @Setter
    private String categoryName = "";

    //计价货类
    @Getter @Setter
    private String pricingCategory = "";

    //安全库存
    @Getter @Setter
    private int safetyStock;

    //吊牌价
    @Getter @Setter
    private double tagPrice;

    //零售价
    @Getter @Setter
    private double retailPrice;

    //成本价
    @Getter @Setter
    private double costPrice;

    //采购价
    @Getter @Setter
    private double purchasePrice;

    //季节编码
    @Getter @Setter
    private String seasonCode = "";

    //季节名称
    @Getter @Setter
    private String seasonName = "";

    //品牌编号
    @Getter @Setter
    private String brandId = "";

    //是否需要串号管理, Y/N (默认为N)
    @Getter @Setter
    private String isSNMgmt = "N";

    //是否需要保质期管理, Y/N (默认为N)
    @Getter @Setter
    private String isShelfLifeMgmt = "N";

    //保质期 (小时)
    @Getter @Setter
    private int shelfLife;

    //保质期禁收天数
    @Getter @Setter
    private int rejectLifecycle;

    //保质期禁售天数
    @Getter @Setter
    private int lockupLifecycle;

    //保质期临期预警天数
    @Getter @Setter
    private int adventLifecycle;

    //是否需要批次管理, Y/N (默认为N)
    @Getter @Setter
    private String isBatchMgmt = "N";

    //批次代码
    @Getter @Setter
    private String batchCode = "";

    //包装代码
    @Getter @Setter
    private String packCode = "";

    //箱规
    @Getter @Setter
    private String pcs = "";

    //商品的原产地
    @Getter @Setter
    private String originAddress = "";

    //批准文号
    @Getter @Setter
    private String approvalNumber = "";

    //是否易碎品, Y/N,  (默认为N)
    @Getter @Setter
    private String isFragile = "N";

    //是否危险品, Y/N,  (默认为N)
    @Getter @Setter
    private String isHazardous = "N";

    //商品包装材料类型
    @Getter @Setter
    private String packageMaterial = "";

    //备注
    @Getter @Setter
    private String remark = "";

    //创建时间,YYYY-MM-DD HH:MM:SS
    @Getter @Setter
    private String createTime;

    //更新时间,YYYY-MM-DD HH:MM:SS
    @Getter @Setter
    private String updateTime;

    //是否有效, Y/N (默认为Y)
    @Getter @Setter
    private String validate = "Y";

    //扩展字段


    //客户
    @Getter @Setter
    private String customerName;

    //供应商
    @Getter @Setter
    private String supplierName;

    //规格
    private String spec;

    //规格
    public String getSpec() { return length+"*"+width+"*"+height+"="+volume; }

    //商品类型描述
    private String skuTypeName;

    //商品类型描述
    public String getSkuTypeName() {
        return comm.SkuType.get(skuType);
    }
}
