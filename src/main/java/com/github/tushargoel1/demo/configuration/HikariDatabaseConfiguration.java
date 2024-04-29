package com.github.tushargoel1.demo.configuration;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HikariDatabaseConfiguration {
    @Value ("${datasource.driverClassName}")
    private String driverClass;

    @Value ("${datasource.url}")
    private String dbURL;

    @Value ("${datasource.username}")
    private String userName;

    @Value ("${datasource.password}")
    private String password;

    @Value ("${spring.datasource.hikari.jdbiMaxPoolSize}")
    private int jdbiMaxPoolSize;

    @Value ("${spring.datasource.hikari.jdbiMinPoolSize}")
    private int jdbiMinPoolSize;

    @Value ("${spring.datasource.hikari.maxIdleTime}")
    private int maxIdleTime;

    @Value ("${spring.datasource.hikari.maxLifeTime}")
    private int maxLifeTime;

    @Value ("${spring.datasource.hikari.testStatement}")
    private String testStatement;

    @Bean
    public HikariDataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("Hikari-JDBI-Pool");
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(dbURL);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setConnectionTimeout(1000); //Setting timeout to 1 second (default is 30 seconds)
        dataSource.setMaximumPoolSize(jdbiMaxPoolSize);
        dataSource.setIdleTimeout(maxIdleTime);
        dataSource.setMaxLifetime(maxLifeTime);
        dataSource.setConnectionInitSql(testStatement);
        dataSource.setMinimumIdle(jdbiMinPoolSize);
        dataSource.setAllowPoolSuspension(true);
        return dataSource;
    }

    @Bean
    public Jdbi jdbi(HikariDataSource hikariDataSource) {
        Jdbi jdbi = Jdbi.create(hikariDataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
