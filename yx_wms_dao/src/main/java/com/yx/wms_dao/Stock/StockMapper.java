package com.yx.wms_dao.Stock;

import com.yx.model.Stock.Stock;
import com.yx.model.Stock.StockCustomerContact;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface StockMapper {

    //获取客户公司列表
    List<Stock> GetStockList(HashMap<String, String> filter);

    //获取客户公司详情
    Stock GetStockInfo(String stockId);

    //新增客户公司
    int InsertStock(Stock stock);

    //修改客户公司信息
    int UpdateStock(Stock stock);

    //修改数据状态
    int UpdateDataValidate(@Param("stockId") String stockId, @Param("validate") int validate);

    //获取客户公司与子公司的关联关系
    List<StockCustomerContact> GetStockCustomerContactList(@Param("groupId")String groupId, @Param("stockId")String stockId);

    //删除客户公司与子公司的关联关系
    int DeleteStockCustomerContact(String stockId);

    //新增客户公司与子公司的关联关系
    int InsertStockCustomerContact(List<StockCustomerContact> list);

    //修改仓库关联客户数量
    int UpdateStockCustomerCount(@Param("stockId")String stockId, @Param("customerCount")int customerCount);
}
