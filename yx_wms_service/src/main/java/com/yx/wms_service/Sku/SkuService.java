package com.yx.wms_service.Sku;

import com.github.pagehelper.PageHelper;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.model.Sku.Sku;
import com.yx.utility.CommFunc;
import com.yx.wms_dao.Sku.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.yx.model.Base.comm.DataId;
import static com.yx.model.Base.comm.DataValidate;

@Service
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;

    //获取商品列表
    public GridResponse GetSkuList(String groupId, GridRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        List<Sku> list = skuMapper.GetSkuList(filterList);

        return new GridResponse(list);
    }

    //获取商品详情
    public Sku GetSkuInfo(String skuId) {
        return skuMapper.GetSkuInfo(skuId);
    }

    //新增商品
    public int InsertSku(Sku sku) {
        sku.setSkuId(CommFunc.GetId(DataId.GetKey("商品")));
        sku.setValidate(DataValidate.GetKey("可用"));

        return skuMapper.InsertSku(sku);
    }

    //修改商品
    public int UpdateSku(Sku sku) {
        return skuMapper.UpdateSku(sku);
    }

    //修改数据状态
    public int UpdateDataValidate(String skuId, int validate) {
        return skuMapper.UpdateDataValidate(skuId, validate);
    }
}
