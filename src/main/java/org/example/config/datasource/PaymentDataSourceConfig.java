package org.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PaymentDataSourceConfig extends AbstractDataSourceConfig {

    @Bean("paymentHikariConfig")
    @Primary
    @ConfigurationProperties("spring.datasource.payment")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean("paymentDataSource")
    public HikariDataSource hikariDataSource(@Qualifier("paymentHikariConfig") HikariConfig hikariConfig) throws Exception{
        return getHikariDataSource(hikariConfig);
    }

}
