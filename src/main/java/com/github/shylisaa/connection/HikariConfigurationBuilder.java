package com.github.shylisaa.connection;

import com.zaxxer.hikari.HikariConfig;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public class HikariConfigurationBuilder {

    private String host, datBaseName, userName, password;
    private int port;

    private final HikariConfig hikariConfig;

    private HikariConfigurationBuilder() {
        this.hikariConfig = new HikariConfig();
    }

    public static HikariConfigurationBuilder builder() {
        return new HikariConfigurationBuilder();
    }

    public HikariConfigurationBuilder host(String host) {
        this.host = host;
        return this;
    }

    public HikariConfigurationBuilder database(String datBaseName) {
        this.datBaseName = datBaseName;
        return this;
    }

    public HikariConfigurationBuilder port(int port) {
        this.port = port;
        return this;
    }

    public HikariConfigurationBuilder username(String userName) {
        this.userName = userName;
        return this;
    }

    public HikariConfigurationBuilder password(String password) {
        this.password = password;
        return this;
    }

    public HikariConfigurationBuilder setDefaultPropertyValues() {
        this.hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        this.hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        this.hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.hikariConfig.addDataSourceProperty("useLocalSessionState", "true");
        this.hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        this.hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "true");
        this.hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
        this.hikariConfig.addDataSourceProperty("elideSetAutoCommits", "true");
        this.hikariConfig.addDataSourceProperty("maintainTimeStats", "true");
        return this;
    }

    public HikariConfigurationBuilder setPropertyValue(String propertyName, Object propertyValue) {
        this.hikariConfig.addDataSourceProperty(propertyName, propertyValue);
        return this;
    }

    public HikariConfig buildConfig() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/citybuild");
        this.hikariConfig.setUsername(this.userName);
        this.hikariConfig.setPassword(this.password);
        return this.hikariConfig;
    }
}
