package com.sk.dao;

/**
 * Created by shisong on 16/7/11.
 */

import com.sk.model.User;

import java.util.List;

public interface UserDAO {

    void save(User u);

    List<User> list();

}