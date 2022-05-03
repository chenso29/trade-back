package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.ReturnAmountByProduct;
import com.trade_accounting.models.dto.finance.ReturnAmountByProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReturnAmountByProductMapper {

    ReturnAmountByProductDto toDto(ReturnAmountByProduct returnAmountByProduct);

    ReturnAmountByProduct toModel(ReturnAmountByProductDto returnAmountByProductDto);
    
}
