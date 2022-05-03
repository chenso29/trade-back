package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ContractorService extends AbstractService<ContractorDto>,
        SearchableService<Contractor, ContractorDto> {

    List<ContractorDto> getAll(String searchTerm);
}
