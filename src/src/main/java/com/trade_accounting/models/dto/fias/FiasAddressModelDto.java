package src.main.java.com.trade_accounting.models.dto.fias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiasAddressModelDto {
    private Long id;
    private String aolevel;
    private String formalname;
    private String shortname;
    private String aoguid;
    private String parentguid;
}
