package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfPriceDto {

    private Long id;

    private String name;

    private String sortNumber;

    public TypeOfPriceDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
