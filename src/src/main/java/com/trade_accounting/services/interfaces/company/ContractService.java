package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ContractService extends AbstractService<ContractDto>, SearchableService<Contract, ContractDto> {

    List<ContractDto> getAll(String searchContr);
}
