package com.preving.intranet.ssggapi.conf.database.prod;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile("prod")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.preving.restapi.personasapi.dao"})
public class PostgreDataBaseProdConf {

    @Value("${datasource.db-postgre.jndi-name}")
    private String postgreJndiName;


    @Bean(name = "postgresqlDataSource", destroyMethod = "")
    public DataSource postgresqlDataSource() throws Exception {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(this.postgreJndiName);
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
