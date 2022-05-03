package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.TypeOfContractorMapper;
import org.mapstruct.factory.Mappers;

public class TypeOfContractorDtoStubs {
    private static final TypeOfContractorMapper mapper = Mappers.getMapper(TypeOfContractorMapper.class);

    public static TypeOfContractorDto getTypeOfContractorDto(Long id) {
        return mapper.toDto(
                ModelStubs.getTypeOfContractor(id)
        );
    }
}
