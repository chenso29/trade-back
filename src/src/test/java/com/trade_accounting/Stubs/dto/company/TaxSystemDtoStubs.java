package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.TaxSystemDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.TaxSystemMapper;
import org.mapstruct.factory.Mappers;

public class TaxSystemDtoStubs {
    private static final TaxSystemMapper mapper = Mappers.getMapper(TaxSystemMapper.class);

    public static TaxSystemDto getTaxSystemDto(Long id) {
        return mapper.toDto(
                ModelStubs.getTaxSystem(id)
        );
    }
}
