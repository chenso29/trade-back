package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.company.ContractorDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContractorMapper {
    //Contractor
    default ContractorDto contractorToContractorDto(Contractor contractor) {
        ContractorDto dto = new ContractorDto();
        if (contractor == null) {
            return null;
        } else {
            dto.setId(contractor.getId());
            dto.setName(contractor.getName());
            dto.setShortname(contractor.getShortname());
            dto.setSortNumber(contractor.getSortNumber());
            dto.setPhone(contractor.getPhone());
            dto.setFax(contractor.getFax());
            dto.setEmail(contractor.getEmail());
            dto.setCommentToAddress(contractor.getCommentToAddress());
            dto.setComment(contractor.getComment());
            dto.setDiscountCardNumber(contractor.getDiscountCardNumber());

            dto.setContactIds(
                    contractor.getContact().stream()
                            .map(Contact::getId)
                            .collect(Collectors.toList())
            );

            dto.setBankAccountIds(
                    contractor.getBankAccounts().stream()
                            .map(BankAccount::getId)
                            .collect(Collectors.toList())
            );

            if (contractor.getAddress() == null) {
                return null;
            } else {
                dto.setAddressId(contractor.getAddress().getId());

                if (contractor.getContractorGroup() == null) {
                    return null;
                } else {
                    dto.setContractorGroupId(contractor.getContractorGroup().getId());

                    if (contractor.getTypeOfPrice() == null) {
                        return null;
                    } else {
                        dto.setTypeOfPriceId(contractor.getTypeOfPrice().getId());

                        if (contractor.getLegalDetail() == null) {
                            return null;
                        } else {
                            dto.setLegalDetailId(contractor.getLegalDetail().getId());

                            if (contractor.getContractorStatus() == null) {
                                return null;
                            } else {
                                dto.setContractorStatusId(contractor.getContractorStatus().getId());

                                if (contractor.getAccessParameters() == null) {
                                    return null;
                                } else {
                                    dto.setAccessParametersId(contractor.getAccessParameters().getId());
                                    return dto;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //    @Mappings({
//            @Mapping(target = "contractorGroup", ignore = true),
//            @Mapping(target = "typeOfPrice", ignore = true),
//            @Mapping(target = "legalDetail", ignore = true),
//            @Mapping(target = "address", ignore = true),
//            @Mapping(target = "contractorStatus", ignore = true),
//            @Mapping(target = "accessParameters", ignore = true),
//            @Mapping(target = "contact", ignore = true),
//            @Mapping(target = "bankAccounts", ignore = true)
//    })
//    Contractor contractorDtoToContractor(ContractorDto contractorDto);
    default Contractor contractorDtoToContractor(ContractorDto contractorDto) {
        if (contractorDto == null) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId(contractorDto.getId());
        contractor.setName(contractorDto.getName());
        contractor.setShortname(contractorDto.getShortname());
        contractor.setSortNumber(contractorDto.getSortNumber());
        contractor.setPhone(contractorDto.getPhone());
        contractor.setFax(contractorDto.getFax());
        contractor.setEmail(contractorDto.getEmail());
        contractor.setCommentToAddress(contractorDto.getCommentToAddress());
        contractor.setComment(contractorDto.getComment());
        contractor.setDiscountCardNumber(contractorDto.getDiscountCardNumber());

        return contractor;
    }


//    @Mappings({
//            @Mapping(source = "contractorGroup.id", target = "contractorGroupId"),
//            @Mapping(source = "typeOfPrice.id", target = "typeOfPriceId"),
//            @Mapping(source = "legalDetail.id", target = "legalDetailId"),
//            @Mapping(source = "address.id", target = "addressId"),
//            @Mapping(source = "contractorStatus.id", target = "contractorStatusId"),
//            @Mapping(source = "accessParameters.id", target = "accessParametersId"),
//    })
//    ContractorDto contractorToContractorDto(Contractor contractor);
//
//    @AfterMapping
//    default void listContactIdToListContactIds(Contractor contractor, @MappingTarget ContractorDto contractorDto) {
//        if (contractor.getContact() == null) {
//            contractorDto.setContactIds(null);
//        } else {
//            List<Long> contactIds = contractor.getContact().stream()
//                    .map(Contact::getId)
//                    .collect(Collectors.toList());
//            contractorDto.setContactIds(contactIds);
//        }
//    }
//
//    @AfterMapping
//    default void listBankAccountsIdToListBankAccountIds(Contractor contractor, @MappingTarget ContractorDto contractorDto) {
//        if (contractor.getBankAccounts() == null) {
//            contractorDto.setBankAccountIds(null);
//        } else {
//            List<Long> bankAccountIds = contractor.getBankAccounts().stream()
//                    .map(BankAccount::getId).collect(Collectors.toList());
//            contractorDto.setBankAccountIds(bankAccountIds);
//        }
//    }
//
//    @Mappings({
//            @Mapping(source = "contractorGroupId", target = "contractorGroup.id"),
//            @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id"),
//            @Mapping(source = "legalDetailId", target = "legalDetail.id"),
//            @Mapping(source = "addressId", target = "address.id"),
//            @Mapping(source = "contractorStatusId", target = "contractorStatus.id"),
//            @Mapping(source = "accessParametersId", target = "accessParameters.id"),
//    })
//    Contractor contractorDtoToContractor(ContractorDto contractorDto);
//
//
//    @AfterMapping
//    default void listContactIdsToListContact(ContractorDto contractorDto, @MappingTarget Contractor contractor, @Context ContactRepository contactRepository) {
//        if (contractorDto.getContactIds() == null) {
//            contractor.setContact(null);
//        } else {
//            List<Contact> contact = contractorDto.getContactIds()
//                    .stream()
//                    .map(contactRepository::getOne)
//                    .collect(Collectors.toList());
//            contractor.setContact(contact);
//        }
//    }
//
//    @AfterMapping
//    default void listBankAccountIdsToListBankAccounts(ContractorDto contractorDto, @MappingTarget Contractor contractor, @Context BankAccountRepository bankAccountRepository) {
//        if (contractorDto.getBankAccountIds() == null) {
//            contractor.setBankAccounts(null);
//        } else {
//            List<BankAccount> bankAccounts = contractorDto.getBankAccountIds()
//                    .stream()
//                    .map(bankAccountRepository::getOne)
//                    .collect(Collectors.toList());
//            contractor.setBankAccounts(bankAccounts);
//        }
//    }
}
