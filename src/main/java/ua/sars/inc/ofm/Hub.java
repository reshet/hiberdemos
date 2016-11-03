package ua.sars.inc.ofm;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Hub {

    @Column(name = "code")
    private String code;

    public Hub() {
    }

    public Hub(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
