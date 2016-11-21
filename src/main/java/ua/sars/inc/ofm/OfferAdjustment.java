package ua.sars.inc.ofm;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_adjustment")
public class OfferAdjustment {

//    @Embedded
//    private RegionDefinition regionDefinition;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "offer_id")
    private Long offerId;

    public OfferAdjustment(Long offerId, RegionDefinition regionDefinition) {
        this.offerId = offerId;
        //this.regionDefinition = regionDefinition;
    }

    public OfferAdjustment() {
    }

//    public RegionDefinition getRegionDefinition() {
//        return regionDefinition;
//    }

//    public void setRegionDefinition(RegionDefinition regionDefinition) {
//        this.regionDefinition = regionDefinition;
//    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getId() {
        return id;
    }
}
