package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

public interface TypeOfContractorService extends AbstractService<TypeOfContractorDto> {

    TypeOfContractorDto getByName(String name);
}
