package ua.sars.inc.ofm;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import ua.sars.inc.DatabaseTestConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    DatabaseTestConfig.class,
    IdGeneratorRepositoryTest.TestContextConfiguration.class
})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class IdGeneratorRepositoryTest {

    @Autowired
    IdGeneratorRepository generatorRepository;

    @Test
    @DatabaseTearDown(value = "/dbunit/ofm/reset.xml", type = DatabaseOperation.TRUNCATE_TABLE)
    public void saves_entity_into_db_with_id() {
        EntityWithIdGenerator entityWithIdGenerator = new EntityWithIdGenerator();
        generatorRepository.saveEntityWithId(entityWithIdGenerator);
        assertThat(entityWithIdGenerator.getId()).isNotNull();
    }

    @Configuration
    static class TestContextConfiguration {
        @Bean
        public IdGeneratorRepository idGeneratorRepository() {
            return new IdGeneratorRepository();
        }
    }
}
