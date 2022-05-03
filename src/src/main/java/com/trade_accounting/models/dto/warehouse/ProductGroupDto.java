package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupDto {

    private Long id;

    private String name;

    private String sortNumber;

    private Boolean serviceGroup = false;

    private Long parentId;

    public ProductGroupDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }

    public ProductGroupDto(String name, String sortNumber, Long parentId) {
        this(name, sortNumber);
        this.parentId = parentId;
    }

    public ProductGroupDto(Long id, String name, String sortNumber, Long parentId) {
        this.id = id;
        this.name = name;
        this.sortNumber = sortNumber;
        this.parentId = parentId;
    }
}
