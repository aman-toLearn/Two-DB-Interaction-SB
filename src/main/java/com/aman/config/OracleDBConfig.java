package com.aman.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.aman.repository.prod"
                        , entityManagerFactoryRef = "oraEMF",
                          transactionManagerRef = "oraTxMgmr")
@EnableTransactionManagement
public class OracleDBConfig {


   @Bean(name = "OraDs")
   @ConfigurationProperties(prefix = "oracle.datasource")
   @Primary
    public DataSource createOraDs(){

       return   DataSourceBuilder.create().build();
    }

    @Bean(name = "oraEMF")
    @Primary
    public LocalContainerEntityManagerFactoryBean createOraEMFB(EntityManagerFactoryBuilder builder){
       //prepare Oracle Hiberante properties

        Map<String,String> props = new HashMap<>();

        props.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.show_sql","true");

        //create and return LocalContainerEntityManagerFactoryBean class object
        // which is a FactoryBean giving EntityManagerFactoryBean class object

        return builder
                .dataSource(createOraDs())
                .packages("com.aman.model.prod")
                .properties(props)
                .build();

    }


    @Bean(name = "oraTxMgmr")
    @Primary
    public JpaTransactionManager createOraTxMgmr( @Qualifier("oraEMF") EntityManagerFactory factory){

       return new JpaTransactionManager(factory);
    }
}
