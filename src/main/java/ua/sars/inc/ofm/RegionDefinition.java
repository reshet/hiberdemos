package ua.sars.inc.ofm;

import java.util.Collection;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class RegionDefinition {

    @GeneratedValue
    @Id
    private Long id;

    private Collection<Hub> hubs;

    RegionDefinition() {}

    RegionDefinition(List<Hub> hubs) {
        this.hubs = hubs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Hub> getHubs() {
        return hubs;
    }

    public void setHubs(Collection<Hub> hubs) {
        this.hubs = hubs;
    }
}
