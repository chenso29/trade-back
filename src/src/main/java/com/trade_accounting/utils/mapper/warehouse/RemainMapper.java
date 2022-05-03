package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Remain;
import com.trade_accounting.models.dto.warehouse.RemainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RemainMapper {
    @Mappings({
            @Mapping(source = "unit.id", target = "unitId")
    })
    RemainDto toDto(Remain remain);

    @Mappings({
            @Mapping(source = "unitId", target = "unit.id")
    })
    Remain toModel(RemainDto remainDto);
}
