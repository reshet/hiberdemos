package ua.sars.inc.ofm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "generator_provided_entity")
public class EntityWithIdGenerator {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "id")
    protected Long id;

    public EntityWithIdGenerator() {}

    public Long getId() {
        return id;
    }
}
