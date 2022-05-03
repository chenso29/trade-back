package src.main.java.com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ReturnToSupplierService extends AbstractService<ReturnToSupplierDto>,
        SearchableService<ReturnToSupplier, ReturnToSupplierDto> {

    List<ReturnToSupplierDto> searchByString(String nameFilter);

}
