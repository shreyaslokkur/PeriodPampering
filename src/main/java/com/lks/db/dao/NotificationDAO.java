package com.lks.db.dao;

import com.lks.core.NotificationStatus;
import com.lks.db.qo.NotificationQO;

import java.util.List;

/**
 * Created by lokkur.
 */
public interface NotificationDAO {

    int addNotification(NotificationQO notificationQO);

    NotificationQO getNotificationByIdForRead(int notificationId);
    NotificationQO getNotificationByVehicleNumberForRead(String vehicleNumber);

    List<NotificationQO> getNotificationByNotificationStatus(NotificationStatus notificationStatus);
    List<String> getValidVehicleNumberPresentInList(List<String> licenseNumberList);
    List<NotificationQO> getNotificationByCreatedDtsRange(long startDts, long endDts);

    boolean updateNotification(NotificationQO notificationQO);
}
