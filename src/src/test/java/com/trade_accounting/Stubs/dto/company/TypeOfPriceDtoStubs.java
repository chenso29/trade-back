package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.TypeOfPriceDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.TypeOfPriceMapper;
import org.mapstruct.factory.Mappers;

public class TypeOfPriceDtoStubs {
    private static final TypeOfPriceMapper mapper = Mappers.getMapper(TypeOfPriceMapper.class);

    public static TypeOfPriceDto getTypeOfPriceDto(Long id) {
        return mapper.toDto(ModelStubs.getTypeOfPrice(id));
    }
}
