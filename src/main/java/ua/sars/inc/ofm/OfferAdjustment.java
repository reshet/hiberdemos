package ua.sars.inc.ofm;

import javax.persistence.Column;
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

    public OfferAdjustment(Long offerId) {
        this.offerId = offerId;
    }

    public OfferAdjustment() {}

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
