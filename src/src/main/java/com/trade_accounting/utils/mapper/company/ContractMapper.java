package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    default Contract toModel(ContractDto contractDto) {
        if (contractDto == null) {
            return null;
        }

        return Contract.builder()
                .id(contractDto.getId())
                .number(contractDto.getNumber())
                .amount(contractDto.getAmount())
                .archive(contractDto.getArchive())
                .comment(contractDto.getComment())
                .build();
    }

    default ContractDto toDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        if (contract == null || contract.getBankAccount().getId() == null || contract.getCompany().getId() == null
                || contract.getContractor().getId() == null || contract.getLegalDetail().getId() == null) {
            return null;
        } else {
            contractDto.setId(contract.getId());
            contractDto.setNumber(contract.getNumber());
            contractDto.setContractDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getContractDate()));
            contractDto.setCompanyId(contract.getCompany().getId());
            contractDto.setBankAccountId(contract.getBankAccount().getId());
            contractDto.setContractorId(contract.getContractor().getId());
            contractDto.setAmount(contract.getAmount());
            contractDto.setArchive(contract.getArchive());
            contractDto.setComment(contract.getComment());
            contractDto.setLegalDetailId(contract.getLegalDetail().getId());
            return contractDto;
        }
    }

    List<ContractDto> toListDto(List<Contract> contracts);
}

