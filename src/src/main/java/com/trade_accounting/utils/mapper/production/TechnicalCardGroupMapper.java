package src.main.java.com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCardGroup;
import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalCardGroupMapper {
    //TechnicalCardGroup
    TechnicalCardGroupDto toDto(TechnicalCardGroup technicalCardGroup);

    TechnicalCardGroup toModel(TechnicalCardGroupDto technicalCardGroupDto);
}
