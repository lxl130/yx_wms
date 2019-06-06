package com.yx.wms_dao.Company;

import com.yx.model.Company.CompanyContact;
import com.yx.model.Company.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CustomerMapper {

    //获取客户公司列表
    List<Customer> GetCustomerList(HashMap<String, String> filter);

    //获取客户公司详情
    Customer GetCustomerInfo(String customerId);

    //新增客户公司
    int InsertCustomer(Customer customer);

    //修改客户公司信息
    int UpdateCustomer(Customer customer);

    //修改数据状态
    int UpdateDataValidate(@Param("customerId")String customerId, @Param("validate")int validate);

    //获取客户公司与子公司的关联关系
    List<CompanyContact> GetCompanyContactList(@Param("groupId")String groupId, @Param("customerId")String customerId);

    //删除客户公司与子公司的关联关系
    int DeleteCompanyContact(String customerId);

    //新增客户公司与子公司的关联关系
    int InsertCompanyContact(CompanyContact contact);

    //获取用户拥有的客户列表
    List<Customer> GetUserCustomerList(String userId);
}
