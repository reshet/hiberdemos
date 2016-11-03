package ua.sars.inc.ofm;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional("myTXManager")
public class OfferAdjustmentService {

    @PersistenceContext(unitName = "ua.sars.inc")
    private EntityManager em;


    public void saveOfferAdjustment(Long offerDefinitionId, List<HubDTO> hubs) {
        List<Hub> hubEntities = hubs.stream().map(OfferAdjustmentService::toHub).collect(Collectors.toList());
        RegionDefinition rd = new RegionDefinition(hubEntities);
        OfferAdjustment offerAdjustment = new OfferAdjustment(offerDefinitionId, rd);
        em.persist(rd);
    }
    
    private static Hub toHub(HubDTO dto) {
        return new Hub(dto.getCode());
    }
}