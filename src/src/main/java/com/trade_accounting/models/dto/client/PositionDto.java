package src.main.java.com.trade_accounting.models.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long id;

    private String name;

    private String sortNumber;

    public PositionDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
