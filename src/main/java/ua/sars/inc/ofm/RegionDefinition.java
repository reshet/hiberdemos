package ua.sars.inc.ofm;

import java.util.Collection;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Embeddable
public class RegionDefinition {

    private Collection<Hub> hubs;

    RegionDefinition() {}

    RegionDefinition(List<Hub> hubs) {
        this.hubs = hubs;
    }

    public Collection<Hub> getHubs() {
        return hubs;
    }

    public void setHubs(Collection<Hub> hubs) {
        this.hubs = hubs;
    }
}
