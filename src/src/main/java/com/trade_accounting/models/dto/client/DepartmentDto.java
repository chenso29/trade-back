package src.main.java.com.trade_accounting.models.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private String sortNumber;


    public DepartmentDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
