package com.yx.wms_web.Stock;

import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.model.Stock.Stock;
import com.yx.model.Stock.StockCustomerContact;
import com.yx.wms_service.Stock.StockService;
import com.yx.wms_web.Base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value={"/Stock"})
public class StockController extends BaseController {
    @Autowired
    private StockService stockService;

    //我的客户首页
    @RequestMapping(value={"","/"})
    public String Stock() {
        return "Stock/stock";
    }

    @ResponseBody
    @RequestMapping(value = "/GetStockList",method = RequestMethod.POST)
    public GridResponse<Stock> GetStockList(@RequestBody GridRequest request) {
        return stockService.GetStockList(GetGroupId(), request);
    }

    //获取公司详细信息
    @ResponseBody
    @RequestMapping("/GetStockInfo")
    public Stock GetStockInfo(String stockId) {
        return stockService.GetStockInfo(stockId);
    }

    //保存客户公司信息
    @ResponseBody
    @RequestMapping("/SaveStock")
    public int SaveStock(@RequestBody Stock stock) {
        if(stock.getStockId() == null || stock.getStockId().equals("")){
            stock.setGroupId(GetGroupId());
            stock.setCreateUser(GetUserId());
            return stockService.InsertStock(stock);
        }
        else{
            stock.setUpdateUser(GetUserId());
            return stockService.UpdateStock(stock);
        }
    }

    //修改公司的数据状态
    @ResponseBody
    @RequestMapping("/UpdateDataValidate")
    public int UpdateDataValidate(String stockId, int validate) {
        return stockService.UpdateDataValidate(stockId, validate);
    }

    //获取仓库与客户的关联关系
    @ResponseBody
    @RequestMapping("/GetStockCustomerContactList")
    public List<StockCustomerContact> GetStockCustomerContactList(String stockId) {
        return stockService.GetStockCustomerContactList(GetGroupId(), stockId);
    }

    //保存仓库与客户的关联关系
    @ResponseBody
    @RequestMapping("/SaveStockCustomerContact")
    public int SaveStockCustomerContact(String stockId, String companyId, @RequestBody List<StockCustomerContact> list) {
        return stockService.SaveStockCustomerContact(stockId, companyId, GetUserId(), list);
    }
}
