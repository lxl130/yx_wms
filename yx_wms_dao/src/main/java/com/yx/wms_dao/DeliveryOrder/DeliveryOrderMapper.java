package com.yx.wms_dao.DeliveryOrder;

import com.yx.model.DeliveryOrder.DeliveryOrder;
import com.yx.model.DeliveryOrder.DeliveryOrderItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface DeliveryOrderMapper {

    //获取发货单列表
    List<DeliveryOrder> GetDeliveryOrderList(HashMap<String, String> filter);

    //获取发货单详情
    DeliveryOrder GetDeliveryOrderInfo(String deliveryOrderId);

    //获取发货单明细
    List<DeliveryOrderItem> GetDeliveryOrderItems(String deliveryOrderId);

    //新增发货单
    int InsertDeliveryOrder(DeliveryOrder deliveryOrder);

    //修改发货单信息
    int UpdateDeliveryOrder(DeliveryOrder deliveryOrder);
}
