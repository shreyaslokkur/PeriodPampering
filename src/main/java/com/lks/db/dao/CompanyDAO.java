package com.lks.db.dao;

import com.lks.db.qo.CompanyQO;

/**
 * Created by lokkur.
 */
public interface CompanyDAO {

    int addCompany(CompanyQO companyQO);

    CompanyQO getCompanyByIdForRead(int companyId);
}
