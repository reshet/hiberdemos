package ua.sars.inc.metamodels;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.Type;

import ua.sars.inc.DatabaseTestConfig;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseTestConfig.class)
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class MetaModelTest {

    @Autowired
    EntityManagerFactory em;

    @Test
    public void checks_managed_meta_models() {
        Metamodel mm = em.getMetamodel();
        Set<ManagedType<?>> managedTypes = mm.getManagedTypes();
        assertEquals(managedTypes.size(), 1);
        ManagedType itemType = managedTypes.iterator().next();
        assertEquals(
            itemType.getPersistenceType(),
            Type.PersistenceType.ENTITY
        );

    }

}
