package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

import java.util.List;

public interface ContractorStatusService extends AbstractService<ContractorStatusDto> {
    List<ContractorStatusDto> getAll();
}
