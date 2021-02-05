package com.preving.intranet.ssggapi.conf.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.preving.restapi.personasapi.dao.facturacion"})
public class PostgreDataBaseConf {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix="postgresql.datasource")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplatePostgresql")
    public JdbcTemplate jdbcTemplateApp(@Qualifier("postgresqlDataSource") DataSource dsApp) {
        return new JdbcTemplate(dsApp);
    }

    @Bean(name = "namedTemplatePostgresql")
    public NamedParameterJdbcTemplate namedTemplateApp(@Qualifier("postgresqlDataSource") DataSource dsApp) {
        return new NamedParameterJdbcTemplate(dsApp);
    }

    @Bean(name= "manualTransactionManagerPostgresql")
    public DataSourceTransactionManager manualTransactionManagerPostgresql(@Qualifier("postgresqlDataSource") DataSource dsApp) {
        return new DataSourceTransactionManager(dsApp);
    }

}
