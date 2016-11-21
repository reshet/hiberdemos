package ua.sars.inc.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${sarsdb.url}")
    private String databaseUrl;

    @Value("${sarsdb.username}")
    private String databaseUser;

    @Value("${sarsdb.password}")
    private String databasePassword;

    @Value("${sarsdb.driver}")
    private String driverClassName;

    @Value("${sarsdb.database}")
    private String database;

    @Value("${sarsdb.showSql}")
    private boolean showSql;

    @Value("${sarsdb.generateDdl}")
    private boolean generateDdl;

    @DependsOn("flyway")
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.valueOf(database));
        vendorAdapter.setGenerateDdl(generateDdl);
        vendorAdapter.setShowSql(showSql);

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ua.sars.inc.ofm");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        factory.setPersistenceUnitName("ua.sars.inc");


        return factory;
    }

    @Bean(name = "myTXManager")
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.default_schema", "ofm");

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:/sars/db/migrations/main/");
        flyway.setSchemas(new String[]{"ofm"});
        flyway.setDataSource(dataSource());
        return flyway;
    }
}
