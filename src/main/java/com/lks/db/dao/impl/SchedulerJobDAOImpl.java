package com.lks.db.dao.impl;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.SchedulerJobDAO;
import com.lks.db.dao.rowmapper.SchedulerJobRowMapper;
import com.lks.db.qo.BhavQO;
import com.lks.db.qo.SchedulerJobQO;
import com.lks.scheduler.SchedulerJobStatus;
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

/**
 * Created by lokkur.
 */
public class SchedulerJobDAOImpl implements SchedulerJobDAO {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerJobDAOImpl.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_CREATE_SCHEDULER_JOB = "INSERT INTO SCHEDULER_JOB "
            + "(STATUS, ERROR_MESSAGE, COMPLETED_DTS) VALUES "
            + "(:symbol, :series, :completedDts);";

    private static final String SQL_GET_LATEST_COMPLETED_SCHEDULER_JOB_FOR_READ = "SELECT SJ.* " +
            "FROM SCHEDULER_JOB SJ " +
            "INNER JOIN ( " +
            "SELECT max(COMPLETED_DTS) as MaxDate " +
            "FROM SCHEDULER_JOB) tm on SJ.STATUS = :status and SJ.COMPLETED_DTS = tm.MaxDate";

    private static final String SQL_UPDATE_SCHEDULER_JOB = "UPDATE SCHEDULER_JOB SJ SET SJ.STATUS = :status, SJ.ERROR_MESSAGE = :errorMessage, SJ.COMPLETED_DTS = :completedDts WHERE SJ.ID = :id";


    private static final String ID = "id";
    private static final String STATUS = "status";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String COMPLETED_DTS = "completedDTS";


    @Override
    public int addSchedulerJob(SchedulerJobQO schedulerJobQO) {
        logger.info("Entering addSchedulerJob {}", schedulerJobQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(STATUS, schedulerJobQO.getStatus());
            namedParameters.addValue(ERROR_MESSAGE, schedulerJobQO.getErrorMessage());
            namedParameters.addValue(COMPLETED_DTS, currentTimeMillis);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_CREATE_SCHEDULER_JOB, namedParameters, keyHolder);
            logger.info("Exiting addSchedulerJob {}", affectedRowCount);

            if (affectedRowCount == 1) {
                return keyHolder.getKey().intValue();
            } else {
                return 0;
            }
        } catch (DuplicateKeyException dke) {
            logger.error("addSchedulerJob - Duplicate key addition to Scheduler Job DB : ", dke);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, dke.getMessage(), dke);
        } catch (DataAccessException de) {
            logger.error("addSchedulerJob - Problem adding Scheduler Job to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("addSchedulerJob - Problem adding Scheduler Job to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public SchedulerJobQO getLatestCompletedSchedulerJob() {
        logger.info("Entering getLatestCompletedSchedulerJob");
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(STATUS, SchedulerJobStatus.COMPLETED.name());
            SchedulerJobQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_LATEST_COMPLETED_SCHEDULER_JOB_FOR_READ,
                    namedParameters, new SchedulerJobRowMapper());
            logger.info("Exiting getLatestCompletedSchedulerJob");
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getLatestCompletedSchedulerJob - No scheduled jobs found");
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getLatestCompletedSchedulerJob - Problem getting Scheduler Job from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getLatestCompletedSchedulerJob - Problem getting Scheduler Job from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getLatestCompletedSchedulerJob - Problem getting Scheduler Job from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }


    @Override
    public boolean updateSchedulerJob(SchedulerJobQO schedulerJobQO) {
        logger.info("Entering updateSchedulerJob: {}", schedulerJobQO);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(ID, schedulerJobQO.getId());
            namedParameters.addValue(STATUS, schedulerJobQO.getStatus());
            namedParameters.addValue(ERROR_MESSAGE, schedulerJobQO.getErrorMessage());
            namedParameters.addValue(COMPLETED_DTS, currentTimeMillis);
            int update = namedParameterJdbcTemplate.update(
                    SQL_UPDATE_SCHEDULER_JOB,
                    namedParameters);
            logger.info("Exiting updateSchedulerJob: {}", schedulerJobQO);
            if(update > 0) {
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException de) {
            logger.error("updateSchedulerJob - Problem updating Scheduler Job : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("updateSchedulerJob - Unable to update Scheduler Job for id {} from DB : {}", schedulerJobQO.getId(), th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

}
