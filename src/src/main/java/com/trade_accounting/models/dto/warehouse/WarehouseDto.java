package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarehouseDto {

    private Long id;

    private String name;

    private String sortNumber;

    private String address;

    private String commentToAddress;

    private String comment;

    public WarehouseDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
