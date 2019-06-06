package com.yx.wms_web.Base;

import com.yx.model.Base.KeyValuePair;
import com.yx.model.Base.comm;
import com.yx.model.Company.Company;
import com.yx.model.Company.Customer;
import com.yx.model.Company.Supplier;
import com.yx.model.Stock.Express;
import com.yx.model.Stock.Stock;
import com.yx.wms_service.Base.SelectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value={"/SelectList"})
public class SelectListController extends BaseController {
    @Autowired
    private SelectListService selectListService;

    //获取用户拥有的客户列表
    @ResponseBody
    @RequestMapping("/GetUserCustomerSelectList")
    public List<Customer> GetUserCustomerSelectList() {
        return selectListService.GetUserCustomerSelectList(GetGroupId());
    }

    //获取客户的供应商列表
    @ResponseBody
    @RequestMapping("/GetCustomerSupplierSelectList")
    public List<Supplier> GetCustomerSupplierSelectList(String customerId) {
        return selectListService.GetCustomerSupplierSelectList(customerId);
    }

    //获取我的公司列表
    @ResponseBody
    @RequestMapping("/GetUserCompanySelectList")
    public List<Company> GetUserCompanySelectList() {
        return selectListService.GetUserCompanySelectList(GetGroupId());
    }

    //获取我的公司列表
    @ResponseBody
    @RequestMapping("/GetUserStockSelectList")
    public List<Stock> GetUserStockSelectList() {
        return selectListService.GetUserStockSelectList(GetGroupId());
    }

    //获取物流公司列表
    @ResponseBody
    @RequestMapping("/GetExpressSelectList")
    public List<Express> GetExpressSelectList() {
        return selectListService.GetExpressSelectList();
    }

    //获取仓库类型下拉列表
    @ResponseBody
    @RequestMapping("/GetStockTypeSelectList")
    public List<KeyValuePair> GetStockTypeSelectList(){
        return comm.GetEnumSelectList(comm.StockType);
    }

    //获取销售平台下拉列表
    @ResponseBody
    @RequestMapping("/GetPlatformSelectList")
    public List<KeyValuePair> GetPlatformSelectList(){
        return comm.GetEnumSelectList(comm.Platform);
    }

    //获取是否数据的下拉列表
    @ResponseBody
    @RequestMapping("/GetDataWhetherSelectList")
    public List<KeyValuePair> GetDataWhetherSelectList(){
        return comm.GetEnumSelectList(comm.DataWhether);
    }

    //获取商品类型的下拉列表
    @ResponseBody
    @RequestMapping("/GetSkuTypeSelectList")
    public List<KeyValuePair> GetSkuTypeSelectList(){
        return comm.GetEnumSelectList(comm.SkuType);
    }

    //获取商品类型的下拉列表
    @ResponseBody
    @RequestMapping("/GetSeasonTypeSelectList")
    public List<KeyValuePair> GetSeasonTypeSelectList(){
        return comm.GetEnumSelectList(comm.SeasonType);
    }

    //获取发货单类型的下拉列表
    @ResponseBody
    @RequestMapping("/GetDeliveryOrderTypeSelectList")
    public List<KeyValuePair> GetDeliveryOrderTypeSelectList(){
        return comm.GetEnumSelectList(comm.DeliveryOrderType);
    }
}
