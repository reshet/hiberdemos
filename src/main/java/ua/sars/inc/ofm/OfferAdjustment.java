package ua.sars.inc.ofm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_adjustment")
public class OfferAdjustment {

//    @Embedded
//    private RegionDefinition regionDefinition;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;

    @Column(name = "offer_id")
    private Long offerId;

    protected OfferAdjustment(Long offerId) {
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
}
