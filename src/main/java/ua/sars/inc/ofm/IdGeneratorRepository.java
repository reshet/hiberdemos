package ua.sars.inc.ofm;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional("myTXManager")
public class IdGeneratorRepository {

    @PersistenceContext(unitName = "ua.sars.inc")
    private EntityManager em;

    public EntityWithIdGenerator saveEntityWithId(EntityWithIdGenerator entityWithIdGenerator) {
        em.persist(entityWithIdGenerator);
        return entityWithIdGenerator;
    }
}