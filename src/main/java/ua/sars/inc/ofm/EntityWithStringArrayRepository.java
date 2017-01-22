package ua.sars.inc.ofm;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional("myTXManager")
public class EntityWithStringArrayRepository {

    @PersistenceContext(unitName = "ua.sars.inc")
    private EntityManager em;

    public EntityWithStringArray saveEntity(EntityWithStringArray entityWithStringArray) {
        em.persist(entityWithStringArray);
        return entityWithStringArray;
    }
}