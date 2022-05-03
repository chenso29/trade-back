package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.TypeOfContractor;
import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfContractorMapper {
    //TypeOfContractor
    TypeOfContractorDto toDto(TypeOfContractor typeOfContractor);

    TypeOfContractor toModel(TypeOfContractorDto typeOfContractorDto);
}
