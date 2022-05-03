package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    //Company
    @Mappings({
            @Mapping(source = "address.id", target = "addressId"),
            @Mapping(source = "legalDetail.id", target = "legalDetailDtoId")
    })
    CompanyDto toDto(Company company);

    @AfterMapping
    default void listBankAccountsIdToListBankAccountDtoIds(Company company, @MappingTarget CompanyDto companyDto) {
        if (company.getBankAccounts() == null) {
            companyDto.setBankAccountDtoIds(null);
        } else {
            List<Long> bankAccountDtoIds = company.getBankAccounts().stream()
                    .map(o -> o.getId()).collect(Collectors.toList());
            companyDto.setBankAccountDtoIds(bankAccountDtoIds);
        }
    }

    @Mappings({
            @Mapping(source = "addressId", target = "address.id"),
            @Mapping(source = "legalDetailDtoId", target = "legalDetail.id")
    })
    Company toModel(CompanyDto companyDto);

    @AfterMapping
    default void listBankAccountsDtoIdsToListBankAccount(CompanyDto companyDto, @MappingTarget Company company, @Context BankAccountRepository bankAccountRepository) {
        if (companyDto.getBankAccountDtoIds() == null) {
            company.setBankAccounts(null);
        } else {
            List<BankAccount> bankAccounts = companyDto.getBankAccountDtoIds()
                    .stream()
                    .map(id -> bankAccountRepository.getOne(id))
                    .collect(Collectors.toList());
            company.setBankAccounts(bankAccounts);
        }
    }
}
