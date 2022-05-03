package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.CompanyMapper;
import org.mapstruct.factory.Mappers;

public class CompanyDtoStubs {
    private static final CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);
    public static CompanyDto getCompanyDto(Long id) {
        return mapper.toDto(
                ModelStubs.getCompany(id)
        );
    }
}
