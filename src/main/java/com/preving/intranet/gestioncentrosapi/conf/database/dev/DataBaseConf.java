package com.preving.intranet.gestioncentrosapi.conf.database.dev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.preving.intranet.gestioncentrosapi.model.dao"})
public class DataBaseConf {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix="datasource.gc-postgresql")
    @Primary
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix="datasource.gc-oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleNamedTemplate")
    public NamedParameterJdbcTemplate oracleNamedTemplate(@Qualifier("oracleDataSource") DataSource dsApp) {
        return new NamedParameterJdbcTemplate(dsApp);
    }

}
