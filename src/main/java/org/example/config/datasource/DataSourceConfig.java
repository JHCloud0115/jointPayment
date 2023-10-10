package org.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public HikariConfig hikariConfig() throws Exception{
        return new HikariConfig();
    }

    @Bean
    public HikariDataSource hikariDataSource(HikariConfig hikariConfig) throws Exception{
        return new HikariDataSource(hikariConfig);
    }

}
