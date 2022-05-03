package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.CorrectionProductDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.CorrectionProductMapper;
import org.mapstruct.factory.Mappers;

public class CorrectionProductDtoStubs {
    private static final CorrectionProductMapper mapper = Mappers.getMapper(CorrectionProductMapper.class);

    public static CorrectionProductDto getCorrectionProductDto(Long id) {
        return mapper.toDto(ModelStubs.getCorrectionProduct(id));
    }
}
