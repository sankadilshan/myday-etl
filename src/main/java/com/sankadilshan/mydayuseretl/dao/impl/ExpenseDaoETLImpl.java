package com.sankadilshan.mydayuseretl.dao.impl;

import com.sankadilshan.mydayuseretl.dao.ExpenseDaoETL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class ExpenseDaoETLImpl implements ExpenseDaoETL {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoETLImpl.class);
    NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    private ExpenseDaoETLImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedTemplate= namedParameterJdbcTemplate;
    }


    @Override
    public void deleteAll() {
        logger.info("Expense  ETL DAO level :: delete all data");
    }

    @Override
    public Map<String, Object> retrieveAll() {
        return null;
    }

    @Override
    public Map<String, Object> insertAll() {
        return null;
    }
}
