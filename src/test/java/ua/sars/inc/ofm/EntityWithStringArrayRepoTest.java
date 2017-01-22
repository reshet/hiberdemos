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

import java.util.Arrays;

import ua.sars.inc.DatabaseTestConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    DatabaseTestConfig.class,
    EntityWithStringArrayRepoTest.TestContextConfiguration.class
})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class EntityWithStringArrayRepoTest {

    @Autowired
    EntityWithStringArrayRepository repository;

    @Test
    @DatabaseSetup(value = "/dbunit/ofm/emptyStringArrays.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "/dbunit/ofm/emptyStringArrays.xml")
    @ExpectedDatabase(value = "/dbunit/ofm/expectedStringArrays.xml", assertionMode = DatabaseAssertionMode.NON_STRICT,
        table = "entity_with_string_array")
    public void saves_entity_with_string_array_into_db() {
        EntityWithStringArray entityWithStringArray = new EntityWithStringArray();
        entityWithStringArray.stringArray = new MyStringArray(Arrays.asList(1,2,3,4,5));
        assertThat(entityWithStringArray.stringArray.toString()).isEqualTo("1 2 3 4 5");
        repository.saveEntity(entityWithStringArray);
    }

    @Configuration
    static class TestContextConfiguration {
        @Bean
        public EntityWithStringArrayRepository repository() {
            return new EntityWithStringArrayRepository();
        }
    }
}
