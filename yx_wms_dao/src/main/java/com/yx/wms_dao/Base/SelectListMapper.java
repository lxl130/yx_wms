package com.yx.wms_dao.Base;

import com.yx.model.Company.Company;
import com.yx.model.Company.Customer;
import com.yx.model.Company.Supplier;
import com.yx.model.Stock.Express;
import com.yx.model.Stock.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectListMapper {
    //获取用户拥有的客户列表
    List<Customer> GetUserCustomerSelectList(String userId);

    //获取用户拥有的公司列表
    List<Company> GetUserCompanySelectList(String groupId);

    //获取用户拥有的仓库列表
    List<Stock> GetUserStockSelectList(String userId);

    //获取销售平台列表
    List<Customer> GetPlatformSelectList();

    //获取销售店铺列表
    List<Customer> GetShopSelectList(String customerId);

    //获取物流公司列表
    List<Express> GetExpressSelectList();

    //获取客户的供应商列表
    List<Supplier> GetCustomerSupplierSelectList(String customerId);
}
