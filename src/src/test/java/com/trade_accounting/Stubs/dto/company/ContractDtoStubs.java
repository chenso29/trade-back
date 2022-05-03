package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.ContractDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.ContractMapper;
import org.mapstruct.factory.Mappers;

public class ContractDtoStubs {
    private static final ContractMapper mapper = Mappers.getMapper(ContractMapper.class);
    public static ContractDto getContractDto(Long id) {
        return mapper.toDto(ModelStubs.getContract(id));
    }
}
