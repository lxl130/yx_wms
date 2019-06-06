package com.yx.wms_web.DeliveryOrder;

import com.yx.model.Base.comm;
import com.yx.model.DeliveryOrder.DeliveryOrder;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.wms_service.DeliveryOrder.DeliveryOrderService;
import com.yx.wms_web.Base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/DeliveryOrder"})
public class DeliveryOrderController extends BaseController {
    @Autowired
    private DeliveryOrderService deliveryOrderService;

    //发货单首页
    @RequestMapping(value={"","/"})
    public String DeliveryOrder() {
        return "DeliveryOrder/deliveryOrder";
    }

    @ResponseBody
    @RequestMapping(value="/GetDeliveryOrderList", method=RequestMethod.POST)
    public GridResponse<DeliveryOrder> GetDeliveryOrderList(@RequestBody GridRequest request) {
        return deliveryOrderService.GetDeliveryOrderList(GetGroupId(), request);
    }

    //获取发货单详细信息
    @ResponseBody
    @RequestMapping("/GetDeliveryOrderInfo")
    public DeliveryOrder GetDeliveryOrderInfo(String deliveryOrderId) {
        return deliveryOrderService.GetDeliveryOrderInfo(deliveryOrderId);
    }

    //保存发货单信息
    @ResponseBody
    @RequestMapping("/SaveDeliveryOrder")
    public int SaveDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        if(deliveryOrder.getDeliveryOrderId() == null || deliveryOrder.getDeliveryOrderId().equals("")){
            deliveryOrder.setGroupId(GetGroupId());
            deliveryOrder.setCreateUser(GetUserId());
            deliveryOrder.setIsUrgency(comm.DataWhether.GetKey("N"));
            deliveryOrder.setDeliveryOrderFlag(comm.DeliveryOrderFlag.GetKey("上门"));
            return deliveryOrderService.InsertDeliveryOrder(deliveryOrder);
        }
        else{
            deliveryOrder.setUpdateUser(GetUserId());
            return deliveryOrderService.UpdateDeliveryOrder(deliveryOrder);
        }
    }
}
