package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.ContractorMapper;
import org.mapstruct.factory.Mappers;

public class ContractorDtoStubs {
    private static final ContractorMapper mapper = Mappers.getMapper(ContractorMapper.class);

    public static ContractorDto getContractorDto(Long id) {
        return mapper.contractorToContractorDto(
                ModelStubs.getContractor(id)
        );
    }
}
