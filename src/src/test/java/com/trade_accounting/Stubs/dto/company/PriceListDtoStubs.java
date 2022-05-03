package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.Stubs.model.company.PriceListModelStubs;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.utils.mapper.company.PriceListMapper;
import org.mapstruct.factory.Mappers;

public class PriceListDtoStubs {

    private static final PriceListMapper mapper = Mappers.getMapper(PriceListMapper.class);

    public static PriceListDto getDto(Long id){
        return mapper.toDto(PriceListModelStubs.getPriceList(id));
    }
}
