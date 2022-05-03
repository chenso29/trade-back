package src.main.java.com.trade_accounting.models.entity.units;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "digital_code")
    private String digitalCode;

    @Column(name = "letter_code")
    private String letterCode;

    @Column(name = "sort_number")
    private String sortNumber;

    public Currency(String shortName, String digitalCode, String letterCode) {
        this.shortName = shortName;
        this.digitalCode = digitalCode;
        this.letterCode = letterCode;
    }

    public Currency(String shortName, String fullName, String digitalCode, String letterCode) {
        this(shortName, digitalCode, letterCode);
        this.fullName = fullName;
    }
}
