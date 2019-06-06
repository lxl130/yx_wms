package com.yx.wms_service.Company;

import com.yx.model.Company.Company;
import com.yx.model.Global.GridRequest;
import com.yx.wms_dao.Company.CompanyMapper;
import com.yx.utility.CommFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.yx.model.Base.comm.DataId;
import static com.yx.model.Base.comm.DataValidate;

@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    //获取公司列表
    public List<Company> GetMyCompanyList(String groupId, GridRequest request) {
        HashMap<String, String> filterList = request.getFilter().getFilterList();
        filterList.put("groupId", groupId);
        return companyMapper.GetMyCompanyList(filterList);
    }

    //获取公司详情
    public Company GetCompanyInfo(String companyId) {
        return companyMapper.GetCompanyInfo(companyId);
    }

    //新增公司
    public int InsertCompany(Company company) {
        company.setCompanyId(CommFunc.GetId(DataId.GetKey("我的公司")));
        company.setValidate(DataValidate.GetKey("可用"));

        return companyMapper.InsertCompany(company);
    }

    //修改公司
    public int UpdateCompany(Company company) {
        return companyMapper.UpdateCompany(company);
    }

    //修改数据状态
    public int UpdateDataValidate(String companyId, int validate) {
        return companyMapper.UpdateDataValidate(companyId, validate);
    }
}