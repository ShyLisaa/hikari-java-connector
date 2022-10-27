package com.github.shylisaa;

import com.github.shylisaa.connection.HikariConfigurationBuilder;
import com.github.shylisaa.executor.ISqlExecutor;
import com.github.shylisaa.executor.SqlExecutor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public class HikariJavaConnector {

    private static HikariJavaConnector instance;
    private Connection connection;
    private final ISqlExecutor executor;

    public HikariJavaConnector(HikariConfigurationBuilder configurationBuilder) {
        instance = this;
        HikariConfig config = configurationBuilder.buildConfig();
        HikariDataSource dataSource = new HikariDataSource(config);

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        this.executor = new SqlExecutor(this.connection);


    }

    @Deprecated
    public static HikariJavaConnector getInstance() {
        return instance;
    }
    public ISqlExecutor getExecutor() {
        return executor;
    }
}
