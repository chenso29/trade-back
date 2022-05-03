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
public class DistrictDto {
    private Long id;
    private String name;
    RegionDto regionDto;
    List<CityDto> citiesDto;
}
