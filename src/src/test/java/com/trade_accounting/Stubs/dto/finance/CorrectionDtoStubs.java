package src.test.java.com.trade_accounting.Stubs.dto.finance;

import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.finance.CorrectionMapper;
import org.mapstruct.factory.Mappers;

public class CorrectionDtoStubs {
    private static final CorrectionMapper mapper = Mappers.getMapper(CorrectionMapper.class);

    public static CorrectionDto getCorrectionDto(Long id) {
        return mapper.toDto(ModelStubs.getCorrection(id));
    }
}
