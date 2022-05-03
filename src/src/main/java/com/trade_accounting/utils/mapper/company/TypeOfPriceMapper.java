package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.TypeOfPrice;
import com.trade_accounting.models.dto.company.TypeOfPriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfPriceMapper {
    //TypeOfPrice
    TypeOfPriceDto toDto(TypeOfPrice typeOfPrice);

    TypeOfPrice toModel(TypeOfPriceDto typeOfPriceDto);
}
