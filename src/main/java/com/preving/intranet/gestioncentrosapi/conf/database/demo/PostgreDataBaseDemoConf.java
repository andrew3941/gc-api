package com.preving.intranet.gestioncentrosapi.conf.database.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile("demo")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.preving.intranet.gestioncentrosapi.model.dao"})
public class PostgreDataBaseDemoConf {

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

}
