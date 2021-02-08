package com.preving.intranet.ssggapi.conf.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
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
        basePackages = {"com.preving.intranet.ssggapi.model.dao.ora2Postgre"})
public class DataBaseConf {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.preving.intranet.ssggapi.model.domain")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Primary
    @Bean(name= "manualTransactionManager")
    public DataSourceTransactionManager manualTransactionManager(@Qualifier("dataSource") DataSource dsApp) {
        return new DataSourceTransactionManager(dsApp);
    }

    //********************************************** SECUENCIAS **************************************
//
//    /*
//     *   tabla "CF_PERIODOS"
//     */
//    @Bean(name="periodoSeq")
//    public DataFieldMaxValueIncrementer periodoSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PERIODOS_SEQ");
//        return MVIncrement;
//    }
//
//    /*
//     *   tabla "CF_DISTRIBUCION_MAESTRA"
//     */
//    @Bean(name="distribucionMaestraSeq")
//    public DataFieldMaxValueIncrementer distribucionMaestraSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_DISTR_MAESTRA_SEQ");
//        return MVIncrement;
//    }
//
//    /*
//     *   tabla "CF_PERIODOS_DISTRIBUCION"
//     */
//    @Bean(name="distribucionPeriodoSeq")
//    public DataFieldMaxValueIncrementer distribucionPeriodoSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PERIODOS_DISTR_SEQ");
//        return MVIncrement;
//    }
//
//    /*
//     *   tabla "CF_PROPUESTAS_PENDIENTES"
//     */
//    @Bean(name="propuestasPdtesSeq")
//    public DataFieldMaxValueIncrementer propuestasPdtesSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PROPUESTAS_PDTES_SEQ");
//        return MVIncrement;
//    }
//
//    /*
//     *   tabla "CF_PROPUESTAS_TRATADAS"
//     */
//    @Bean(name="propuestasTratadasSeq")
//    public DataFieldMaxValueIncrementer propuestasTratadasSeq() throws Exception {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PROPUESTAS_TRATADAS_SEQ");
//        return MVIncrement;
//    }
//
//    /*
//     *   tabla "CF_PROCESOS"
//     */
//    @Bean(name="procesosSeq")
//    public DataFieldMaxValueIncrementer procesosSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PROCESOS_SEQ");
//        return MVIncrement;
//    }
//
//    @Bean(name="preCabeceraFacSeq")
//    public DataFieldMaxValueIncrementer preCabeceraFacSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PRE_CABECERAS_FAC_SQ");
//        return MVIncrement;
//    }
//
//    @Bean(name="preLineaFacSeq")
//    public DataFieldMaxValueIncrementer preLineaFacSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PRE_LINEAS_FAC_SQ");
//        return MVIncrement;
//    }
//
//    @Bean(name="preLineaXPropuestaFacSeq")
//    public DataFieldMaxValueIncrementer preLineaXPropuestaFacSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_PRE_LINEAS_X_PROP_FAC_SQ");
//        return MVIncrement;
//    }
//
//    @Bean(name="anulacionSeq")
//    public DataFieldMaxValueIncrementer anulacionSeq() {
//        OracleSequenceMaxValueIncrementer MVIncrement = new OracleSequenceMaxValueIncrementer();
//        MVIncrement.setDataSource(dataSource());
//        MVIncrement.setIncrementerName("NAVISION.CF_ANULACIONES_SQ");
//        return MVIncrement;
//    }
}
