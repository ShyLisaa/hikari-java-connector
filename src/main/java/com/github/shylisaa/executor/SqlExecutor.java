package com.github.shylisaa.executor;

import com.github.shylisaa.executor.type.DatabaseType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public class SqlExecutor implements ISqlExecutor {

    private final Connection connection;
    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(String table, String[] keys, DatabaseType[] types) {
        StringBuilder update = new StringBuilder("CREATE TABLE IF NOT EXISTS `").append(table).append("` (`");

        Map<String, DatabaseType> content = new HashMap<>();
        for (int i = 0; i < keys.length; i++) content.put(keys[i], types[i]);

        int count = 0;
        for (String key : content.keySet()) {
            update.append(key).append("` ").append(content.get(key).getDatabaseText()).append(count + 1 >= content.size() ? ")" : ", `");
            count++;
        }
        update(update.toString());
    }

    @Override
    public boolean existsInTable(String table, String columnKey, String columnValue) {
        try (ResultSet resultSet = this.connection.prepareStatement("SELECT * FROM " + table + " WHERE " + columnKey + " ='" + columnValue + "'").executeQuery()) {
            boolean bool = resultSet.next();
            this.connection.endRequest();
            return bool;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public void addMoreInTable(String table, List<String> types, List<Object> list) {
        StringBuilder upload = new StringBuilder("INSERT INTO " + table + "(" + types.get(0));
        for (int i = 1; i < types.size(); i++) upload.append(", ").append(types.get(i));
        upload.append(") VALUES ('").append(list.get(0)).append("'");
        for (int i = 1; i < list.size(); i++) upload.append(", '").append(list.get(i)).append("'");
        upload.append(");");

        update(upload.toString());
    }

    @Override
    public void updateInTable(String table, String keyRow, String keyValue, String setRow, Object setValue) {
        update("UPDATE " + table + " SET " + setRow + " = '" + setValue + "' WHERE " + "= '" + keyValue + "';");
    }

    @Override
    public void updateInTableNull(String table, String keyRow, String keyValue, String setRow) {
        update("UPDATE " + table + " SET " + setRow + "= NULL WHERE " + keyRow + "= '" + keyValue + "';");
    }

    @Override
    public Object getFromTable(String table, String column, String value, String neededColumn) {
        try (ResultSet resultSet = this.connection.prepareStatement("SELECT * FROM " + table + " WHERE " + column + "='" + value + "'").executeQuery()) {
            while (resultSet.next()) {
                Object object = resultSet.getObject(neededColumn);
                this.connection.endRequest();
                return object;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void removeFromTable(String table, String column, String value) {
        update("DELETE FROM " + table + " WHERE " + column + "='" + value + "';");
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void update(String sql) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            this.connection.endRequest();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
