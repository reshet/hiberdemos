package ua.sars.inc.ofm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entity_with_string_array")
public class EntityWithStringArray {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;

    @Convert(
        converter = MyStringArrayAttributeConverter.class,
        disableConversion = false)
    @Column(name = "STRING_ARRAY", length = 1000)
    protected MyStringArray stringArray;

    protected EntityWithStringArray() {}

    public EntityWithStringArray(MyStringArray array) {
        this.stringArray = array;
    }

    public Long getId() {
        return id;
    }

    public List<Integer> getStringArray() {
        return stringArray.getArray();
    }
}
