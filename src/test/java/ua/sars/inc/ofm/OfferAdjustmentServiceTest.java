package ua.sars.inc.ofm;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import ua.sars.inc.DatabaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseTestConfig.class)
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class OfferAdjustmentServiceTest {

    @Autowired
    OfferAdjustmentService adjustmentService;

    @Test
    @DatabaseSetup(value = "/dbunit/ofm/sampleData.xml")
    @ExpectedDatabase(value = "/dbunit/ofm/expectedDataAdd.xml", assertionMode = DatabaseAssertionMode.NON_STRICT, table = "offer_adjustment")
    public void saves_adjustment_into_db() {
        adjustmentService.saveOfferAdjustment(23L, Arrays.asList(
            new HubDTO("123"), new HubDTO("124")
        ));
    }

}
