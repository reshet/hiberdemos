package ua.sars.inc.ofm;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import javax.sql.DataSource;

import ua.sars.inc.config.DatabaseConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = OfferAdjustmentServiceTest.AppConfig.class)
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class OfferAdjustmentServiceTest {

    @Autowired
    OfferAdjustmentService adjustmentService;

    @Test
    @DatabaseSetup("/dbunit/ofm/sampleData.xml")
    @ExpectedDatabase(value = "/dbunit/ofm/expectedDataAdd.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "offer_adjustment")
    public void saves_adjustment_into_db() {
        adjustmentService.saveOfferAdjustment(1L, Arrays.asList(
            new HubDTO("23"), new HubDTO("24")
        ));
    }

    @Configuration
    @Import(DatabaseConfig.class)
    @PropertySource(value = "classpath:/application.properties")
    static class AppConfig {

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

    }
}
