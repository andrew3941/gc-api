package com.preving.intranet.ssggapi.conf.database.prod;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Profile("prod")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.preving.restapi.personasapi.dao"})
public class DataBaseProdConf {

    @Value("${datasource.db-rrhh.jndi-name}")
    private String rrhhJndiName;

    @Primary
    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource dataSourceProd() throws Exception {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(this.rrhhJndiName);
    }

    @Primary
    @Bean(name = "jdbcTemplateApp")
    public JdbcTemplate jdbcTemplateApp(@Qualifier("dataSource") DataSource dsApp) {
        return new JdbcTemplate(dsApp);
    }

    @Bean(name = "namedTemplateApp")
    public NamedParameterJdbcTemplate namedTemplateApp(@Qualifier("dataSource") DataSource dsApp) {
        return new NamedParameterJdbcTemplate(dsApp);
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryProd(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSourceProd) {
        return builder
                .dataSource(dataSourceProd)
                .packages("com.preving.restapi.personasapi.domain")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerProd(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactoryProd) {
        return new JpaTransactionManager(entityManagerFactoryProd);
    }

    @Primary
    @Bean(name= "manualTransactionManager")
    public DataSourceTransactionManager manualTransactionManager(@Qualifier("dataSource") DataSource dsApp) {
        return new DataSourceTransactionManager(dsApp);
    }


}
