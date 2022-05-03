package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.LegalDetailDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.LegalDetailMapper;
import org.mapstruct.factory.Mappers;

public class LegalDetailDtoStubs {
    private static final LegalDetailMapper mapper = Mappers.getMapper(LegalDetailMapper.class);

    public static LegalDetailDto getLegalDetailDto(Long id) {
        return mapper.toDto(ModelStubs.getLegalDetail(1L));
    }
}
