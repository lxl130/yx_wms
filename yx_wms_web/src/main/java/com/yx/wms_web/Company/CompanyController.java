package com.yx.wms_web.Company;

import com.yx.model.Company.Company;
import com.yx.model.Global.GridRequest;
import com.yx.wms_service.Company.CompanyService;
import com.yx.wms_web.Base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value={"/Company"})
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;

    //我的公司首页
    @RequestMapping(value={"","/"})
    public String Company() {
        return "company/company";
    }

    //获取我的公司列表
    @ResponseBody
    @RequestMapping("/GetMyCompanyList")
    public List<Company> GetMyCompanyList(@RequestBody GridRequest request) {
        return companyService.GetMyCompanyList(GetGroupId(), request);
    }

    //获取公司详细信息
    @ResponseBody
    @RequestMapping("/GetCompanyInfo")
    public Company GetCompanyInfo(String companyId) {
        return companyService.GetCompanyInfo(companyId);
    }

    //保存公司信息
    @ResponseBody
    @RequestMapping("/SaveCompany")
    public int SaveCompany(@RequestBody Company company) {
        if(company.getCompanyId() == null || company.getCompanyId().equals("")){
            company.setGroupId(GetGroupId());
            company.setParentId(GetGroupId());
            company.setCreateUser(GetUserId());
            return companyService.InsertCompany(company);
        }
        else{
            company.setUpdateUser(GetUserId());
            return companyService.UpdateCompany(company);
        }
    }

    //修改公司的数据状态
    @ResponseBody
    @RequestMapping("/UpdateDataValidate")
    public int UpdateDataValidate(String companyId, int validate) {
        return companyService.UpdateDataValidate(companyId, validate);
    }
}
