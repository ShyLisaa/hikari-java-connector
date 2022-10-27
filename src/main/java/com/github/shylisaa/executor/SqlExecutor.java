package com.github.shylisaa.executor;

import com.github.shylisaa.executor.ISqlExecutor;
import com.github.shylisaa.executor.type.DatabaseType;

import java.sql.Connection;
import java.util.List;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public class SqlExecutor implements ISqlExecutor {

    private Connection connection;
    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(String tableName, String[] columns, DatabaseType[] databaseTypes) {

    }

    @Override
    public boolean existsInTable(String table, String columnKey, String columnValue) {
        return false;
    }

    @Override
    public void addMoreInTable(String table, List<String> types, List<Object> list) {

    }

    @Override
    public void updateInTable(String table, String keyRow, String keyValue, String setRow, Object setValue) {

    }

    @Override
    public void updateInTableNull(String table, String keyRow, String keyValue, String setRow) {

    }

    @Override
    public Object getFromTable(String table, String column, String value, String neededColumn) {
        return null;
    }

    @Override
    public void removeFromTable(String table, String column, String value) {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void disconnect() {

    }
}
