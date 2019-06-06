package com.yx.wms_service.DeliveryOrder;

import com.github.pagehelper.PageHelper;
import com.yx.model.Base.comm;
import com.yx.model.DeliveryOrder.DeliveryOrder;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.utility.CommFunc;
import com.yx.wms_dao.DeliveryOrder.DeliveryOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.yx.model.Base.comm.DataId;
import static com.yx.model.Base.comm.DeliveryOrderStatus;

@Service
public class DeliveryOrderService {
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;

    //获取发货单列表
    public GridResponse<DeliveryOrder> GetDeliveryOrderList(String groupId, GridRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        List<DeliveryOrder> list = deliveryOrderMapper.GetDeliveryOrderList(filterList);

        return new GridResponse(list);
    }

    //获取发货单详情
    public DeliveryOrder GetDeliveryOrderInfo(String deliveryOrderId) {
        DeliveryOrder order = deliveryOrderMapper.GetDeliveryOrderInfo(deliveryOrderId);
        order.setItems(deliveryOrderMapper.GetDeliveryOrderItems(deliveryOrderId));

        return order;
    }

    //新增发货单
    public int InsertDeliveryOrder(DeliveryOrder deliveryOrder) {
        deliveryOrder.setPlatformName(comm.Platform.get(deliveryOrder.getPlatformId()));
        deliveryOrder.setDeliveryOrderStatus(DeliveryOrderStatus.GetKey("已创建"));
        deliveryOrder.setDeliveryOrderId(CommFunc.GetId(DataId.GetKey("发货单主单")));

        return deliveryOrderMapper.InsertDeliveryOrder(deliveryOrder);
    }

    //修改发货单
    public int UpdateDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryOrderMapper.UpdateDeliveryOrder(deliveryOrder);
    }
}
