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
@EnableJpaRepositories(basePackages = "com.aman.repository.promotions"
        , entityManagerFactoryRef = "mysqlEMF",
        transactionManagerRef = "mysqlTxMgmr")
@EnableTransactionManagement
public class MySQLDBConfig {


    @Bean(name = "mySQLDs")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource createMySQLDs(){

        return   DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlEMF")
    public LocalContainerEntityManagerFactoryBean createMySQLEMF(EntityManagerFactoryBuilder builder){
        //prepare Oracle Hiberante properties

        Map<String,String> props = new HashMap<>();

        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.show_sql","true");

        //create and return LocalContainerEntityManagerFactoryBean class object
        // which is a FactoryBean giving EntityManagerFactoryBean class object

        return builder
                .dataSource(createMySQLDs())
                .packages("com.aman.model.promotions")
                .properties(props)
                .build();

    }


    @Bean(name = "mysqlTxMgmr")
    public JpaTransactionManager createMySQLTxMgmr( @Qualifier("mysqlEMF") EntityManagerFactory factory){

        return new JpaTransactionManager(factory);
    }
}
