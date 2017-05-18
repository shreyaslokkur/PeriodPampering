package com.lks.db.dao;

import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.UserQO;

/**
 * Created by lokkur.
 */
public interface UserDAO {

    int addUser(UserQO userQO);

    UserQO getuserByIdForRead(int userId);
}
