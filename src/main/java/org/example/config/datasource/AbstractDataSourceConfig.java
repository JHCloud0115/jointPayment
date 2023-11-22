package org.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

public abstract class AbstractDataSourceConfig {

    protected abstract HikariConfig hikariConfig();


    protected HikariDataSource getHikariDataSource(HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }

}
