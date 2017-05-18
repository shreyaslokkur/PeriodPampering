package com.lks.db.dao.rowmapper;

import com.lks.db.qo.CompanyQO;
import com.lks.db.qo.RecommendationQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRowMapper implements RowMapper<CompanyQO> {

private static final String ID = "ID";
private static final String PAID_UP_VALUE = "PAID_UP_VALUE";
private static final String ISIN_NUMBER = "ISIN_NUMBER";
private static final String SYMBOL = "SYMBOL";
private static final String NAME_OF_COMPANY = "NAME_OF_COMPANY";
private static final String SERIES = "SERIES";
private static final String MARKET_LOT = "MARKET_LOT";
private static final String FACE_VALUE = "FACE_VALUE";
private static final String CREATED_DTS = "CREATED_DTS";
@Override
public CompanyQO mapRow(ResultSet rs, int rowNum) throws SQLException {
    CompanyQO companyQO = null;
        if (rs != null) {
        companyQO = new CompanyQO();
        companyQO.setId(rs.getInt(ID));
        companyQO.setPaidUpValue(rs.getInt(PAID_UP_VALUE));
        companyQO.setIsinNumber(rs.getString(ISIN_NUMBER));
        companyQO.setSymbol(rs.getString(SYMBOL));
        companyQO.setNameOfCompany(rs.getString(NAME_OF_COMPANY));
        companyQO.setSeries(rs.getString(SERIES));
        companyQO.setMarketLot(rs.getInt(MARKET_LOT));
        companyQO.setFaceValue(rs.getInt(FACE_VALUE));
        companyQO.setCreatedDts(rs.getLong(CREATED_DTS));

        }
        return companyQO;
        }
}
