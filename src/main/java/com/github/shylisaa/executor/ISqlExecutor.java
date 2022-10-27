package com.github.shylisaa.executor;

import com.github.shylisaa.executor.type.DatabaseType;

import java.sql.Connection;
import java.util.List;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public interface ISqlExecutor {

    void createTable(String tableName, String[] columns, DatabaseType[] databaseTypes);

    boolean existsInTable(String table, String columnKey, String columnValue);

    void addMoreInTable(String table, List<String> types, List<Object> list);

    void updateInTable(String table, String keyRow, String keyValue, String setRow, Object setValue);

    void updateInTableNull(String table, String keyRow, String keyValue, String setRow);

    Object getFromTable(String table, String column, String value, String neededColumn);

    void removeFromTable(String table, String column, String value);

    void disconnect();
}
