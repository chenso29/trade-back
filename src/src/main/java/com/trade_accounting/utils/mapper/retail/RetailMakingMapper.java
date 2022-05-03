package src.main.java.com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.entity.retail.RetailStore;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RetailMakingMapper {

    default RetailMakingDto toDto(RetailMaking retailMaking) {
        if (retailMaking == null) {
            return null;
        }

        RetailMakingDto.RetailMakingDtoBuilder makingDtoBuilder = RetailMakingDto.builder();

        makingDtoBuilder.id(retailMaking.getId());
        makingDtoBuilder.date(String.valueOf(retailMaking.getDate()));
        makingDtoBuilder.retailStoreId(retailMaking.getId());
        makingDtoBuilder.fromWhom(retailMaking.getFromWhom());
        makingDtoBuilder.companyId(retailMakingCompanyId(retailMaking));
        makingDtoBuilder.sum(retailMaking.getSum());
        makingDtoBuilder.isPrint(retailMaking.getIsPrint());
        makingDtoBuilder.isSent(retailMaking.getIsSent());
        makingDtoBuilder.comment(retailMaking.getComment());

        return makingDtoBuilder.build();
    }

    default RetailMaking toModel(RetailMakingDto retailMakingDto) {
        if (retailMakingDto == null) {
            return null;
        }

        RetailMaking.RetailMakingBuilder retailMaking = RetailMaking.builder();

        retailMaking.id(retailMakingDto.getId());
        retailMaking.date(LocalDateTime.parse(retailMakingDto.getDate()));
        retailMaking.retailStore(retailMakingDtoToRetailStore(retailMakingDto));
        retailMaking.fromWhom(retailMakingDto.getFromWhom());
        retailMaking.company(retailMakingDtoToCompany(retailMakingDto));
        retailMaking.sum(retailMakingDto.getSum());
        retailMaking.isPrint(retailMakingDto.getIsPrint());
        retailMaking.isSent(retailMakingDto.getIsSent());
        retailMaking.comment(retailMakingDto.getComment());

        return retailMaking.build();
    }

    private Long retailMakingCompanyId(RetailMaking retailMaking) {
        if (retailMaking == null) {
            return null;
        }
        Company company = retailMaking.getCompany();
        if (company == null) {
            return null;
        }
        Long id = company.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Company retailMakingDtoToCompany(RetailMakingDto retailMakingDto) {
        if (retailMakingDto == null) {
            return null;
        }
        Company company = new Company();
        company.setId(retailMakingDto.getCompanyId());
        return company;
    }

    private RetailStore retailMakingDtoToRetailStore(RetailMakingDto retailMakingDto) {
        if (retailMakingDto == null) {
            return null;
        }
        RetailStore.RetailStoreBuilder retailStore = RetailStore.builder();
        retailStore.id(retailMakingDto.getRetailStoreId());
        return retailStore.build();
    }
}
