package src.main.java.com.trade_accounting.models.dto.fias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto {
    private Long id;
    private String name;
    List<DistrictDto> districtDtos;
}
