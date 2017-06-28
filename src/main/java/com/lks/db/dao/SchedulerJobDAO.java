package com.lks.db.dao;

import com.lks.db.qo.SchedulerJobQO;
import com.lks.db.qo.UserQO;

/**
 * Created by lokkur.
 */
public interface SchedulerJobDAO {

    int addSchedulerJob(SchedulerJobQO schedulerJobQO);

    SchedulerJobQO getLatestCompletedSchedulerJob();

    boolean updateSchedulerJob(SchedulerJobQO schedulerJobQO);
}
