package ua.sars.inc;

import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

import javax.sql.DataSource;

import ua.sars.inc.config.DatabaseConfig;
import ua.sars.inc.ofm.OfferAdjustmentService;

@Configuration
@Import(DatabaseConfig.class)
@PropertySource(value = "classpath:/application.properties")
public class DatabaseTestConfig {

    @Value("${sarsdb.database}")
    private String database;

    @Value("${sarsdb.test.url}")
    private String databaseUrl;

    @Value("${sarsdb.username}")
    private String databaseUser;

    @Value("${sarsdb.password}")
    private String databasePassword;

    @Value("${sarsdb.driver}")
    private String driverClassName;

    @Bean
    public OfferAdjustmentService service() {
        return new OfferAdjustmentService();
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
        DatabaseDataSourceConnectionFactoryBean databaseDataSourceConnectionFactoryBean
            = new DatabaseDataSourceConnectionFactoryBean(dataSource());
        databaseDataSourceConnectionFactoryBean.setSchema("ofm");
        return databaseDataSourceConnectionFactoryBean;
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

    // Database config
    @DependsOn("flyway")
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.valueOf(database));
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ua.sars.inc.ofm");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaTestProperties());
        factory.setPersistenceUnitName("ua.sars.inc");
        return factory;
    }

    private Properties jpaTestProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.default_schema", "ofm");

        return properties;
    }

}
