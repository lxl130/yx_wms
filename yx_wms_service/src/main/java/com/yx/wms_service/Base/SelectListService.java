package com.yx.wms_service.Base;

import com.yx.model.Company.Company;
import com.yx.model.Company.Customer;
import com.yx.model.Company.Supplier;
import com.yx.model.Stock.Express;
import com.yx.model.Stock.Stock;
import com.yx.wms_dao.Base.SelectListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectListService {
    @Autowired
    private SelectListMapper selectListMapper;

    //获取公司列表
    public List<Company> GetUserCompanySelectList(String groupId) {
        return selectListMapper.GetUserCompanySelectList(groupId);
    }

    //获取用户拥有的客户列表
    public List<Customer> GetUserCustomerSelectList(String userId) {
        return selectListMapper.GetUserCustomerSelectList(userId);
    }

    //获取用户拥有的仓库列表
    public List<Stock> GetUserStockSelectList(String userId) {
        return selectListMapper.GetUserStockSelectList(userId);
    }

    //获取物流公司列表
    public List<Express> GetExpressSelectList() {
        return selectListMapper.GetExpressSelectList();
    }

    //获取客户的供应商列表
    public List<Supplier> GetCustomerSupplierSelectList(String customerId) {
        return selectListMapper.GetCustomerSupplierSelectList(customerId);
    }
}