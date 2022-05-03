package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.company.SupplierAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SupplierAccountMapper {

    default SupplierAccountDto toDto(SupplierAccount supplierAccount){
            SupplierAccountDto supplierAccountDto = new SupplierAccountDto();
            if(supplierAccount==null) {
                return null;
            } else {
                supplierAccountDto.setId(supplierAccount.getId());
                supplierAccountDto.setIsSpend(supplierAccount.getIsSpend());
                supplierAccountDto.setComment(supplierAccount.getComment());
                supplierAccountDto.setIsRecyclebin(supplierAccount.getIsRecyclebin());
                supplierAccountDto.setDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(supplierAccount.getDate()));
                supplierAccountDto.setTypeOfInvoice(supplierAccount.getTypeOfInvoice().name());
                supplierAccountDto.setPlannedDatePayment(DateTimeFormatter.ISO_LOCAL_DATE
                        .format(supplierAccount.getPlannedDatePayment()));
                Warehouse warehouse = supplierAccount.getWarehouse();
                Company company = supplierAccount.getCompany();
                Contractor contractor = supplierAccount.getContractor();
                Contract contract = supplierAccount.getContract();

                if (warehouse==null){
                    return null;
                } else {
                    supplierAccountDto.setWarehouseId(warehouse.getId());
                    if (company==null) {
                        return null;
                    } else {
                        supplierAccountDto.setCompanyId(company.getId());
                        if (contractor==null) {
                            return null;
                        } else {
                            supplierAccountDto.setContractorId(contractor.getId());
                            if (contract==null) {
                                return null;
                            } else {
                                supplierAccountDto.setContractId(contract.getId());
                                return supplierAccountDto;
                            }
                        }
                    }
                }
            }
        }

    @Mapping(target = "date", ignore = true)
    default SupplierAccount toModel(SupplierAccountDto supplierAccountDto) {
            if (supplierAccountDto == null) {
                return null;
            }

            SupplierAccount.SupplierAccountBuilder supplier = SupplierAccount.builder();

            supplier.company(supplierAccountDtoToCompany(supplierAccountDto));
            supplier.contractor(supplierAccountDtoToContractor(supplierAccountDto));
            supplier.warehouse(supplierAccountDtoToWarehouse(supplierAccountDto));
            supplier.contract(supplierAccountDtoToContract(supplierAccountDto));
            supplier.id(supplierAccountDto.getId());
            if (supplierAccountDto.getDate() != null) {
                supplier.date(LocalDateTime.parse(supplierAccountDto.getDate()));
            }
            if (supplierAccountDto.getPlannedDatePayment() != null) {
                supplier.plannedDatePayment(LocalDateTime.parse(supplierAccountDto.getPlannedDatePayment() + "T00:00"));
            }
            if (supplierAccountDto.getTypeOfInvoice() != null) {
                supplier.typeOfInvoice(Enum.valueOf(TypeOfInvoice.class, supplierAccountDto.getTypeOfInvoice()));
            }
            supplier.isSpend(supplierAccountDto.getIsSpend());
            supplier.isRecyclebin(supplierAccountDto.getIsRecyclebin());
            supplier.comment(supplierAccountDto.getComment());
            return supplier.build();
    }

    default Company supplierAccountDtoToCompany(SupplierAccountDto supplierAccountDto) {
        if (supplierAccountDto == null) {
            return null;
        }

        Company company = new Company();

        company.setId(supplierAccountDto.getCompanyId());

        return company;
    }

    default Contractor supplierAccountDtoToContractor(SupplierAccountDto supplierAccountDto) {
        if (supplierAccountDto == null) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId(supplierAccountDto.getContractorId());

        return contractor;
    }

    default Warehouse supplierAccountDtoToWarehouse(SupplierAccountDto supplierAccountDto) {
        if (supplierAccountDto == null) {
            return null;
        }

        Warehouse.WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.id(supplierAccountDto.getWarehouseId());

        return warehouse.build();
    }

    default Contract supplierAccountDtoToContract(SupplierAccountDto supplierAccountDto) {
        if (supplierAccountDto == null) {
            return null;
        }

        Contract.ContractBuilder contract = Contract.builder();

        contract.id(supplierAccountDto.getContractId());

        return contract.build();
    }
}
