package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface BuyersReturnService extends AbstractService<BuyersReturnDto>, SearchableService<BuyersReturn, BuyersReturnDto> {
    List<BuyersReturnDto> getByContractorId(Long id);

    List<BuyersReturnDto> findBySearch(String text);
}
