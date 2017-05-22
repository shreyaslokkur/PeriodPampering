package com.lks.db.dao;

import com.lks.db.qo.BhavQO;
import com.lks.db.qo.CompanyQO;

/**
 * Created by lokkur.
 */
public interface BhavDAO {

    int addBhav(BhavQO bhavQO);

    BhavQO getBhavByIdForRead(int bhavId);
}
