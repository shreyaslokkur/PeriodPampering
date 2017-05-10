package com.lks.db.dao.impl;

import com.lks.core.MASErrorCodes;
import com.lks.core.MASException;
import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.core.NotificationStatus;
import com.lks.db.dao.NotificationDAO;
import com.lks.db.dao.rowmapper.NotificationRowMapper;
import com.lks.db.qo.NotificationQO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lokkur.
 */
public class NotificationDAOImpl implements NotificationDAO {
    private static final Logger logger = LoggerFactory.getLogger(NotificationDAOImpl.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_CREATE_NOTIFICATION = "INSERT INTO NOTIFICATION "
            + "(NAME, PHONE_NUMBER, EMAIL_ADDRESS, VEHICLE_NUMBER, SERVICE_TYPE, NOTIFICATION_STATUS, CREATED_DTS, NOTIFICATION_DTS, IS_REPEAT) VALUES "
            + "(:name, :phoneNumber, :emailAddress, :vehicleNumber, :serviceType, :notificationStatus, :createdDts, :notificationDts, :isRepeat);";

    private static final String SQL_GET_NOTIFICATION = "SELECT * FROM NOTIFICATION ";

    private static final String SQL_GET_NOTIFICATION_FOR_ID_FOR_READ = "SELECT N.* " +
            "FROM NOTIFICATION N " +
            "WHERE N.ID = :id";

    private static final String SQL_GET_NOTIFICATION_FOR_VEHICLE_NUMBER_FOR_READ = "SELECT N.* " +
            "FROM NOTIFICATION N " +
            "WHERE N.VEHICLE_NUMBER = :vehicleNumber";

    private static final String SQL_GET_LIST_OF_NOTIFICATION_FOR_STATUS_FOR_READ = "SELECT N.* " +
            "FROM NOTIFICATION N " +
            "WHERE N.NOTIFICATION_STATUS = :notificationStatus";

    private static final String SQL_GET_LIST_OF_VALID_VEHICLE_NUMBER_IN_DATABASE = "SELECT N.VEHICLE_NUMBER " +
            "FROM NOTIFICATION N " +
            "WHERE N.VEHICLE_NUMBER in (:vehicleNumberList)";

    private static final String SQL_GET_LIST_OF_NOTIFICATION_BETWEEN_TIMESTAMP_FOR_READ = "SELECT N.* " +
            "FROM NOTIFICATION N " +
            "WHERE N.CREATED_DTS BETWEEN :startingCreatedDts AND :endingCreatedDts";

    private static final String SQL_UPDATE_NOTIFICATION = "UPDATE NOTIFICATION " +
            "SET NOTIFICATION_STATUS = IFNULL(:notificationStatus , NOTIFICATION_STATUS), NOTIFICATION_DTS = :notificationDts " +
            "WHERE ID = :id;";


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String VEHICLE_NUMBER = "vehicleNumber";
    private static final String SERVICE_TYPE = "serviceType";
    private static final String NOTIFICATION_STATUS = "notificationStatus";
    private static final String CREATED_DTS = "createdDts";
    private static final String STARTING_CREATED_DTS = "startedCreatedDts";
    private static final String ENDING_CREATED_DTS = "createdDts";
    private static final String NOTIFICATION_DTS = "notificationDts";
    private static final String IS_REPEAT = "isRepeat";

    @Override
    @Transactional
    public int addNotification(NotificationQO notificationQO) {
        logger.info("Entering addNotification {}", notificationQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(NAME, notificationQO.getName());
            namedParameters.addValue(PHONE_NUMBER, notificationQO.getPhoneNumber());
            namedParameters.addValue(EMAIL_ADDRESS, notificationQO.getEmailAddress());
            namedParameters.addValue(VEHICLE_NUMBER, notificationQO.getVehicleNumber());
            namedParameters.addValue(SERVICE_TYPE, notificationQO.getServiceType());
            namedParameters.addValue(NOTIFICATION_STATUS, NotificationStatus.NOT_NOTIFIED.name());
            namedParameters.addValue(IS_REPEAT, notificationQO.isRepeat());
            namedParameters.addValue(CREATED_DTS, currentTimeMillis);
            namedParameters.addValue(NOTIFICATION_DTS, currentTimeMillis);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_CREATE_NOTIFICATION, namedParameters, keyHolder);
            logger.info("Exiting addNotification {}", affectedRowCount);

            if (affectedRowCount == 1) {
                return keyHolder.getKey().intValue();
            } else {
                return 0;
            }
        } catch (DuplicateKeyException dke) {
            logger.error("addNotification - Duplicate key addition to Notification DB : ", dke);
            throw dke;
        } catch (DataAccessException de) {
            logger.error("addNotification - Problem adding Notification to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("addNotification - Problem adding Notification to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public NotificationQO getNotificationByIdForRead(int notificationId) {
        logger.info("Entering getNotificationByIdForRead {}", notificationId);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(ID, notificationId);
            NotificationQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_NOTIFICATION_FOR_ID_FOR_READ,
                    namedParameters, new NotificationRowMapper());
            logger.info("Exiting getNotificationByIdForRead {}", notificationId);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getNotificationByIdForRead - No Notification found for id  :  {} ", notificationId);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getNotificationByIdForRead - Problem getting Notification for id from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getNotificationByIdForRead - Problem getting Notification for id from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getNotificationByIdForRead - Problem getting Notification for id from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public NotificationQO getNotificationByVehicleNumberForRead(String vehicleNumber) {
        logger.info("Entering getNotificationByVehicleNumberForRead {}", vehicleNumber);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(VEHICLE_NUMBER, vehicleNumber);
            NotificationQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_NOTIFICATION_FOR_VEHICLE_NUMBER_FOR_READ,
                    namedParameters, new NotificationRowMapper());
            logger.info("Exiting getNotificationByVehicleNumberForRead {}", vehicleNumber);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getNotificationByVehicleNumberForRead - No Notification found for id  :  {} ", vehicleNumber);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getNotificationByVehicleNumberForRead - Problem getting Notification for id from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getNotificationByVehicleNumberForRead - Problem getting Notification for id from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getNotificationByVehicleNumberForRead - Problem getting Notification for id from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public List<NotificationQO> getNotificationByNotificationStatus(NotificationStatus notificationStatus) {
        logger.info("Entering getNotificationByNotificationStatus {}", notificationStatus);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(NOTIFICATION_STATUS, notificationStatus);
            List<NotificationQO> resp = namedParameterJdbcTemplate.query(SQL_GET_LIST_OF_NOTIFICATION_FOR_STATUS_FOR_READ, namedParameters,
                    new NotificationRowMapper());
            logger.info("Exiting getNotificationByNotificationStatus {}", notificationStatus);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getNotificationByNotificationStatus - No Notification found for status  :  {} ", notificationStatus);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getNotificationByNotificationStatus - Problem getting Notification for status from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getNotificationByNotificationStatus - Problem getting Notification for status from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getNotificationByNotificationStatus - Problem getting Notification for status from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public List<String> getValidVehicleNumberPresentInList(List<String> vehicleNumberList) {
        logger.info("Entering getValidVehicleNumberPresentInList {}", vehicleNumberList);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource("vehicleNumberList", vehicleNumberList);
            List<String> resp = namedParameterJdbcTemplate.query(SQL_GET_LIST_OF_VALID_VEHICLE_NUMBER_IN_DATABASE, namedParameters, (resultSet, i) -> resultSet.getString(5));
            logger.info("Exiting getValidVehicleNumberPresentInList {}", vehicleNumberList);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getValidVehicleNumberPresentInList - No valid license number found in list  :  {} ", vehicleNumberList);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getValidVehicleNumberPresentInList - Problem getting valid license number from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getValidVehicleNumberPresentInList - Problem getting valid license number from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getValidVehicleNumberPresentInList - Problem getting valid license number from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public List<NotificationQO> getNotificationByCreatedDtsRange(long startDts, long endDts) {
        logger.info("Entering getNotificationByCreatedDtsRange {} {}", startDts, endDts);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(STARTING_CREATED_DTS, startDts);
            namedParameters.addValue(ENDING_CREATED_DTS, endDts);
            List<NotificationQO> resp = namedParameterJdbcTemplate.query(SQL_GET_LIST_OF_NOTIFICATION_BETWEEN_TIMESTAMP_FOR_READ, namedParameters,
                    new NotificationRowMapper());
            logger.info("Exiting getNotificationByCreatedDtsRange {} {}", startDts, endDts);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getNotificationByCreatedDtsRange - No Notification found for date range  :  {} {}", startDts, endDts);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getNotificationByCreatedDtsRange - Problem getting Notification for date range from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getNotificationByCreatedDtsRange - Problem getting Notification for date range from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getNotificationByCreatedDtsRange - Problem getting Notification for date range from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public boolean updateNotification(NotificationQO notificationQO) {
        logger.info("Entering updateNotification {}", notificationQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(NOTIFICATION_STATUS, notificationQO.getNotificationStatus());
            long currentTimeMillis = System.currentTimeMillis();
            namedParameters.addValue(NOTIFICATION_DTS, currentTimeMillis);
            namedParameters.addValue(ID, notificationQO.getId());
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_UPDATE_NOTIFICATION, namedParameters);
            logger.info("Exiting updateNotification {}", affectedRowCount);
            if (affectedRowCount == 1) {
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException de) {
            logger.error("updateNotification - Problem updating Delegated License to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("updateNotification - Problem updating Delegated License to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }
}
