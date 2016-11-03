package ua.sars.inc.ofm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "offer_adjustment")
public class OfferAdjustment {

    private RegionDefinition regionDefinition;

    @Column(name = "offer_id")
    private Long offerId;

    public OfferAdjustment(Long offerId, RegionDefinition regionDefinition) {
        this.offerId = offerId;
        this.regionDefinition = regionDefinition;
    }

    public OfferAdjustment() {
    }

    public RegionDefinition getRegionDefinition() {
        return regionDefinition;
    }

    public void setRegionDefinition(RegionDefinition regionDefinition) {
        this.regionDefinition = regionDefinition;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
}
