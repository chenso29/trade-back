package src.main.java.com.trade_accounting.models.entity.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute_of_calculation_objects")
public class AttributeOfCalculationObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "is_service", columnDefinition = "boolean default false")
    private Boolean isService;

    public AttributeOfCalculationObject(String name, String sortNumber, Boolean isService) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.isService = isService;
    }
}
