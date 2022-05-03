package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.ContractorGroupDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.ContractorGroupMapper;
import org.mapstruct.factory.Mappers;

public class ContractorGroupDtoStubs {
    private static final ContractorGroupMapper mapper = Mappers.getMapper(ContractorGroupMapper.class);
    public static ContractorGroupDto getContractorGroupDto(Long id) {
        return mapper.toDto(
                ModelStubs.getContractorGroup(id)
        );
    }
}
