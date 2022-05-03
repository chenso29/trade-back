package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.models.dto.warehouse.AttributeOfCalculationObjectDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.warehouse.AttributeOfCalculationObjectMapper;
import org.mapstruct.factory.Mappers;

public class AttributeOfCalculationObjectDtoStubs {
    private static final AttributeOfCalculationObjectMapper mapper = Mappers.getMapper(AttributeOfCalculationObjectMapper.class);
    public static AttributeOfCalculationObjectDto getAttributeOfCalculationObjectDto(Long id) {
        return mapper.toDto(
                ModelStubs.getAttributeOfCalculationObject(id)
        );
    }
}
