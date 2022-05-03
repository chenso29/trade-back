package src.test.java.com.trade_accounting.Stubs.dto.company;


import com.trade_accounting.Stubs.model.company.ContractorStatusModelStubs;
import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.utils.mapper.company.ContractorStatusMapper;
import org.mapstruct.factory.Mappers;

public class ContractorStatusDtoStubs {
    private static final ContractorStatusMapper mapper = Mappers.getMapper(ContractorStatusMapper.class);

    public static ContractorStatusDto getDto(Long id){
        return mapper.toDto(ContractorStatusModelStubs.getContractorStatus(id));
    }
}
