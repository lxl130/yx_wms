package com.yx.wms_service.Company;

import com.github.pagehelper.PageHelper;
import com.yx.model.Company.CompanyContact;
import com.yx.model.Company.Customer;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.wms_dao.Company.CustomerMapper;
import com.yx.utility.CommFunc;
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
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    //获取客户公司列表
    public GridResponse<Customer> GetCustomerList(String groupId, GridRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        List<Customer> list = customerMapper.GetCustomerList(filterList);

        return new GridResponse(list);
    }

    //获取公司详情
    public Customer GetCustomerInfo(String customerId) {
        return customerMapper.GetCustomerInfo(customerId);
    }

    //新增公司
    public int InsertCustomer(Customer customer) {
        customer.setCustomerId(CommFunc.GetId(DataId.GetKey("客户公司")));
        customer.setValidate(DataValidate.GetKey("可用"));

        return customerMapper.InsertCustomer(customer);
    }

    //修改公司
    public int UpdateCustomer(Customer customer) {
        return customerMapper.UpdateCustomer(customer);
    }

    //修改数据状态
    public int UpdateDataValidate(String customerId, int validate) {
        return customerMapper.UpdateDataValidate(customerId, validate);
    }

    //获取客户公司与子公司的关联关系
    public List<CompanyContact> GetCompanyContactList(String groupId, String customerId) {
        return customerMapper.GetCompanyContactList(groupId, customerId);
    }

    //保存客户公司与子公司的关联关系
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public int SaveCompanyContact(String customerId, List<CompanyContact> list) {
        int result;
        //先删除关联关系
        result = customerMapper.DeleteCompanyContact(customerId);
        if (result < 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }

        //添加关系关系
        for(CompanyContact contact : list){
            contact.setContactId(CommFunc.GetId(DataId.GetKey("客户与子公司关联")));
            contact.setCustomerId(customerId);
            result = customerMapper.InsertCompanyContact(contact);
            if (result < 1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }

        return result;
    }
}
