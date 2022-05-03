package src.main.java.com.trade_accounting.utils.mapper.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.models.entity.fias.FiasAddressModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FiasAddressModelMapper {
    FiasAddressModel toModel(FiasAddressModelDto dto);

    FiasAddressModelDto toDto(FiasAddressModel model);
}
