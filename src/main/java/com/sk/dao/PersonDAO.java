package com.sk.dao;

/**
 * Created by shisong on 16/7/11.
 */

import com.sk.model.Person;

import java.util.List;

public interface PersonDAO {

    void save(Person p);

    List<Person> list();

}