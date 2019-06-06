package com.yx.model.Base;

import java.util.*;

public class comm
{
    public static class EnumMap extends HashMap<String, String>{
        public String GetKey(String value){
            for (String key : keySet()) {
                if(value.equals(get(key)))
                    return key;
            }
            return "0";
        }
    }

    //数据可用性
    public static final EnumMap DataValidate = new EnumMap();
    static {
        DataValidate.put("0", "默认");
        DataValidate.put("Y", "可用");
        DataValidate.put("N", "停用");
        DataValidate.put("D", "删除");
    }

    //数据ID起始数
    public static final EnumMap DataId = new EnumMap();
    static {
        DataId.put("0", "默认");
        DataId.put("10", "用户");
        DataId.put("11", "用户公司");
        DataId.put("20", "我的公司");
        DataId.put("21", "客户公司");
        DataId.put("22", "供应商公司");
        DataId.put("23", "客户与子公司关联");
        DataId.put("30", "仓库");
        DataId.put("31", "仓库与客户关联");
        DataId.put("35", "仓库库区");
        DataId.put("32", "仓库库位");
        DataId.put("40", "商品");
        DataId.put("50", "发货单主单");
        DataId.put("51", "发货单明细");
    }

    //是·否数据列表
    public static final EnumMap DataWhether = new EnumMap();
    static {
        DataWhether.put("0", "默认");
        DataWhether.put("Y", "是");
        DataWhether.put("N", "否");
    }

    //季节数据列表
    public static final EnumMap SeasonType = new EnumMap();
    static {
        SeasonType.put("0", "默认");
        SeasonType.put("CHUN", "春季");
        SeasonType.put("XIA", "夏季");
        SeasonType.put("QIU", "秋季");
        SeasonType.put("DONG", "冬季");
    }

    //商品类型数据列表
    public static final EnumMap SkuType = new EnumMap();
    static {
        SkuType.put("0", "默认");
        SkuType.put("ZC", "正常商品");
        SkuType.put("FX", "分销商品");
        SkuType.put("ZH", "组合商品");
        SkuType.put("ZP", "赠品");
        SkuType.put("BC", "包材");
        SkuType.put("HC", "耗材");
        SkuType.put("FL", "辅料");
        SkuType.put("XN", "虚拟品");
        SkuType.put("FS", "附属品");
        SkuType.put("CC", "残次品");
        SkuType.put("OTHER", "其它");
    }

    //销售平台类型
    public static final EnumMap Platform = new EnumMap();
    static {
        Platform.put("0", "默认");
        Platform.put("TB", "淘宝");
        Platform.put("JD", "京东");
        Platform.put("VIP", "唯品会");
    }

    //仓库类型
    public static final EnumMap StockType = new EnumMap();
    static {
        StockType.put("0", "默认");
        StockType.put("20", "良品仓");
        StockType.put("21", "残品仓");
        StockType.put("22", "退货仓");
    }

    //仓库区域
    public static final EnumMap StockRegionType = new EnumMap();
    static {
        StockRegionType.put("0", "默认");
        StockRegionType.put("30", "拣货区");
        StockRegionType.put("31", "存储区");
        StockRegionType.put("32", "暂存区");
    }

    //发货单类型
    public static final EnumMap DeliveryOrderType = new EnumMap();
    static {
        DeliveryOrderType.put("0", "默认");
        DeliveryOrderType.put("JYCK", "销售出库单");
        DeliveryOrderType.put("HHCK", "换货出库单");
        DeliveryOrderType.put("BFCK", "补发出库单");
        DeliveryOrderType.put("QTCK", "其他出库单");
    }

    //发货单状态
    public static final EnumMap DeliveryOrderStatus = new EnumMap();
    static {
        DeliveryOrderStatus.put("0", "默认");
        DeliveryOrderStatus.put("10", "已创建");
        DeliveryOrderStatus.put("20", "已审核");
        DeliveryOrderStatus.put("30", "已锁库");
        DeliveryOrderStatus.put("40", "已拣货");
        DeliveryOrderStatus.put("50", "已分拣");
        DeliveryOrderStatus.put("60", "已复核");
        DeliveryOrderStatus.put("70", "已打包");
        DeliveryOrderStatus.put("80", "已称重");
        DeliveryOrderStatus.put("90", "已出库");
    }

    //发货单标记
    public static final EnumMap DeliveryOrderFlag = new EnumMap();
    static {
        DeliveryOrderFlag.put("0", "默认");
        DeliveryOrderFlag.put("COD", "货到付款");
        DeliveryOrderFlag.put("LIMIT", "限时配送");
        DeliveryOrderFlag.put("COMPLAIN", "已投诉");
        DeliveryOrderFlag.put("SPLIT", "拆单");
        DeliveryOrderFlag.put("EXCHANGE", "换货");
        DeliveryOrderFlag.put("VISIT", "上门");
        DeliveryOrderFlag.put("MODIFYTRANSPORT", "是否可改配送方式");
        DeliveryOrderFlag.put("CONSIGN", "物流宝代理发货");
        DeliveryOrderFlag.put("SELLER_AFFORD", "是否卖家承担运费");
        DeliveryOrderFlag.put("FENXIAO", "分销订单");
    }

    public static int GetKey(HashMap<Integer, String> map, String value){
        for (int key : map.keySet()) {
            if(value.equals(map.get(key)))
                return key;
        }
        return 0;
    }

    public static List<KeyValuePair> GetEnumSelectList(EnumMap map){
        ArrayList list = new ArrayList();
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getKey() == "0") continue;
            KeyValuePair pair = new KeyValuePair(entry.getKey(), entry.getValue());
            list.add(pair);
        }
        return list;
    }
}