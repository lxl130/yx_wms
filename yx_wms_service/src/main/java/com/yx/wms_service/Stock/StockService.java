package com.yx.wms_service.Stock;

import com.github.pagehelper.PageHelper;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.model.Stock.Stock;
import com.yx.model.Stock.StockCustomerContact;
import com.yx.utility.CommFunc;
import com.yx.wms_dao.Stock.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;

import static com.yx.model.Base.comm.DataId;
import static com.yx.model.Base.comm.DataValidate;

@Service
public class StockService {
    @Autowired
    private StockMapper stockMapper;

    //获取仓库列表
    public GridResponse GetStockList(String groupId, GridRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        List<Stock> list = stockMapper.GetStockList(filterList);

        return new GridResponse(list);
    }

    //获取仓库详情
    public Stock GetStockInfo(String stockId) {
        return stockMapper.GetStockInfo(stockId);
    }

    //新增仓库
    public int InsertStock(Stock stock) {
        stock.setStockId(CommFunc.GetId(DataId.GetKey("仓库")));
        stock.setValidate(DataValidate.GetKey("可用"));

        return stockMapper.InsertStock(stock);
    }

    //修改仓库
    public int UpdateStock(Stock stock) {
        return stockMapper.UpdateStock(stock);
    }

    //修改数据状态
    public int UpdateDataValidate(String stockId, int validate) {
        return stockMapper.UpdateDataValidate(stockId, validate);
    }

    //获取仓库与客户的关联关系
    public List<StockCustomerContact> GetStockCustomerContactList(String groupId, String stockId) {
        return stockMapper.GetStockCustomerContactList(groupId, stockId);
    }

    //保存仓库与客户的关联关系
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public int SaveStockCustomerContact(String stockId, String companyId, String createrUser, List<StockCustomerContact> list) {
        int result;

        //先删除关联关系
        result = stockMapper.DeleteStockCustomerContact(stockId);
        if (result < 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }

        //添加关联关系
        for(StockCustomerContact contact : list){
            contact.setStockCustomerId(CommFunc.GetId(DataId.GetKey("仓库与客户关联")));
            contact.setStockId(stockId);
            contact.setCompanyId(companyId);
            contact.setCreateUser(createrUser);
        }
        result = stockMapper.InsertStockCustomerContact(list);
        if (result < list.size()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }

        //更新仓库表中的关联客户数量
        result = stockMapper.UpdateStockCustomerCount(stockId, list.size());
        if (result < 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }

        return result;
    }
}
