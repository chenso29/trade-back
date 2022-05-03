package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.company.ContractorGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorGroupMapper {
    //ContractorGroup
    ContractorGroupDto toDto(ContractorGroup contractorGroup);

    ContractorGroup toModel(ContractorGroupDto contractorGroupDto);
}
