package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.RevenueDto;
import com.trade_accounting.Stubs.model.warehouse.RevenueModelStubs;
import com.trade_accounting.utils.mapper.warehouse.RevenueMapper;
import org.mapstruct.factory.Mappers;

public class RevenueDtoStubs {

    private static final RevenueMapper mapper = Mappers.getMapper(RevenueMapper.class);

    public static RevenueDto getDto(Long id) {
        return mapper.toDto(RevenueModelStubs.getRevenue(id));
    }
}
