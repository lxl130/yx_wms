package com.yx.wms_dao.Company;

import com.yx.model.Company.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CompanyMapper {

    //获取我的公司列表
    List<Company> GetMyCompanyList(HashMap<String, String> filter);

    //获取公司详情
    Company GetCompanyInfo(String companyId);

    //新增公司
    int InsertCompany(Company company);

    //修改公司信息
    int UpdateCompany(Company company);

    //修改数据状态
    int UpdateDataValidate(@Param("companyId")String companyId, @Param("validate")int validate);
}
