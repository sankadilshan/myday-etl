package com.sankadilshan.mydayuseretl.dao.sql;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sankadilshan.mydayuseretl.Constants.EXPENSE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseETLSql {

    public static final String DELETE_ALL_QUERY = "TRUNCATE " + EXPENSE;
    public static final String INSERT_QUERY = "INSERT INTO " + EXPENSE + "(userId, amount, type) VALUES(:userId, :amount, :type)";

    public static final String COFFEE_INSERT_QUERY= "INSERT INTO coffee(brand, origin, characteristic) VALUES(:brand, :origin, :characteristic)";
}
