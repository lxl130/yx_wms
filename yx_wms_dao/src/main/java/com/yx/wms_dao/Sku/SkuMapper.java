package com.yx.wms_dao.Sku;

import com.yx.model.Sku.Sku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SkuMapper {

    //获取商品列表
    List<Sku> GetSkuList(HashMap<String, String> filter);

    //获取商品详情
    Sku GetSkuInfo(String skuId);

    //新增商品
    int InsertSku(Sku sku);

    //修改商品信息
    int UpdateSku(Sku sku);

    //修改数据状态
    int UpdateDataValidate(@Param("skuId") String skuId, @Param("validate") int validate);
}
