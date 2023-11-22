package org.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MemberDataSourceConfig extends AbstractDataSourceConfig {

    @Bean("memberHikariConfig")
    @Primary
    @ConfigurationProperties("spring.datasource.member")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean("memberHikariDataSource")
    public HikariDataSource hikariDataSource(@Qualifier("memberHikariConfig") HikariConfig hikariConfig) throws Exception{
        return getHikariDataSource(hikariConfig);
    }

}
