package com.yx.wms_web.Company;

import com.yx.model.Company.CompanyContact;
import com.yx.model.Company.Customer;
import com.yx.model.Global.GridRequest;
import com.yx.model.Global.GridResponse;
import com.yx.wms_service.Company.CustomerService;
import com.yx.wms_web.Base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value={"/Customer"})
public class CustomerController extends BaseController {
    @Autowired
    private CustomerService customerService;

    //我的客户首页
    @RequestMapping(value={"","/"})
    public String Customer() {
        return "Company/customer";
    }

    @ResponseBody
    @RequestMapping(value = "/GetCustomerList",method = RequestMethod.POST)
    public GridResponse<Customer> GetCustomerList(@RequestBody GridRequest request) {
        return customerService.GetCustomerList(GetGroupId(), request);
    }

    //获取公司详细信息
    @ResponseBody
    @RequestMapping("/GetCustomerInfo")
    public Customer GetCustomerInfo(String customerId) {
        return customerService.GetCustomerInfo(customerId);
    }

    //保存客户公司信息
    @ResponseBody
    @RequestMapping("/SaveCustomer")
    public int SaveCustomer(@RequestBody Customer customer) {
        if(customer.getCustomerId() == null || customer.getCustomerId().equals("")){
            customer.setGroupId(GetGroupId());
            customer.setCreateUser(GetUserId());
            return customerService.InsertCustomer(customer);
        }
        else{
            customer.setUpdateUser(GetUserId());
            return customerService.UpdateCustomer(customer);
        }
    }

    //修改公司的数据状态
    @ResponseBody
    @RequestMapping("/UpdateDataValidate")
    public int UpdateDataValidate(String customerId, int validate) {
        return customerService.UpdateDataValidate(customerId, validate);
    }

    //获取客户公司与子公司的关联关系
    @ResponseBody
    @RequestMapping("/GetCompanyContactList")
    public List<CompanyContact> GetCompanyContactList(String customerId) {
        return customerService.GetCompanyContactList(GetGroupId(), customerId);
    }

    //保存客户公司与子公司的关联关系
    @ResponseBody
    @RequestMapping("/SaveCompanyContact")
    public int SaveCompanyContact(String customerId, @RequestBody List<CompanyContact> list) {
        return customerService.SaveCompanyContact(customerId, list);
    }
}
