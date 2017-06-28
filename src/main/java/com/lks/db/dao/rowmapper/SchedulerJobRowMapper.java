package com.lks.db.dao.rowmapper;

import com.lks.db.qo.SchedulerJobQO;
import com.lks.db.qo.UserQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SchedulerJobRowMapper implements RowMapper<SchedulerJobQO> {

    private static final String ID = "ID";
    private static final String STATUS = "STATUS";
    private static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    private static final String COMPLETED_DTS = "COMPLETED_DTS";
@Override
public SchedulerJobQO mapRow(ResultSet rs, int rowNum) throws SQLException {
    SchedulerJobQO schedulerJobQO = null;
        if (rs != null) {
        schedulerJobQO = new SchedulerJobQO();
        schedulerJobQO.setId(rs.getInt(ID));
        schedulerJobQO.setStatus(rs.getString(STATUS));
        schedulerJobQO.setErrorMessage(rs.getString(ERROR_MESSAGE));
        schedulerJobQO.setCompletedDTS(rs.getLong(COMPLETED_DTS));
        }
    return schedulerJobQO;
    }
}
