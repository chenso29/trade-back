package src.main.java.com.trade_accounting.models.entity.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "comment_to_address")
    private String commentToAddress;

    @Column(name = "comment")
    private String comment;

    public Warehouse(String name, String sortNumber, String address, String commentToAddress, String comment) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.address = address;
        this.commentToAddress = commentToAddress;
        this.comment = comment;
    }
}
