package com.sankadilshan.mydayuseretl.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ExpenseDaoETL {

    void deleteAll();
    Map<String, Object> retrieveAll();
    Map<String, Object> insertAll();
}
