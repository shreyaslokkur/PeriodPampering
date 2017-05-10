package com.lks.db.dao.rowmapper;

import com.lks.db.qo.NotificationQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lokkur on 31/03/17.
 */
public class NotificationRowMapper implements RowMapper<NotificationQO> {
private static final String ID = "ID";
private static final String NAME = "NAME";
private static final String PHONE_NUMBER = "PHONE_NUMBER";
private static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
private static final String VEHICLE_NUMBER = "VEHICLE_NUMBER";
private static final String SERVICE_TYPE = "SERVICE_TYPE";
private static final String NOTIFICATION_STATUS = "NOTIFICATION_STATUS";
private static final String CREATED_DTS = "CREATED_DTS";
private static final String NOTIFICATION_DTS = "NOTIFICATION_DTS";
private static final String IS_REPEAT = "IS_REPEAT";
@Override
public NotificationQO mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationQO notificationQO = null;
        if (rs != null) {
        notificationQO = new NotificationQO();
        notificationQO.setId(rs.getInt(ID));
        notificationQO.setName(rs.getString(NAME));
        notificationQO.setPhoneNumber(rs.getString(PHONE_NUMBER));
        notificationQO.setEmailAddress(rs.getString(EMAIL_ADDRESS));
        notificationQO.setVehicleNumber(rs.getString(VEHICLE_NUMBER));
        notificationQO.setServiceType(rs.getString(SERVICE_TYPE));
        notificationQO.setNotificationStatus(rs.getString(NOTIFICATION_STATUS));
        notificationQO.setCreatedDts(rs.getLong(CREATED_DTS));
        notificationQO.setNotificationDts(rs.getLong(NOTIFICATION_DTS));
        notificationQO.setRepeat(rs.getBoolean(IS_REPEAT));

        }
        return notificationQO;
        }
}
