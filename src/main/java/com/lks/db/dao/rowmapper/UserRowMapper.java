package com.lks.db.dao.rowmapper;

import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.UserQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserQO> {

private static final String ID = "ID";
private static final String FIRST_NAME = "FIRST_NAME";
private static final String LAST_NAME = "LAST_NAME";
private static final String FB_ID = "FB_ID";
private static final String CREATED_DTS = "CREATED_DTS";
@Override
public UserQO mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserQO userQO = null;
        if (rs != null) {
        userQO = new UserQO();
        userQO.setId(rs.getInt(ID));
        userQO.setFirstName(rs.getString(FIRST_NAME));
        userQO.setLastName(rs.getString(LAST_NAME));
        userQO.setFbID(rs.getString(FB_ID));
        userQO.setCreatedDts(rs.getLong(CREATED_DTS));

        }
        return userQO;
        }
}
