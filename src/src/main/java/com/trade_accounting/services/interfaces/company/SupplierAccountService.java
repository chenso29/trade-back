package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.dto.company.SupplierAccountDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SupplierAccountService extends AbstractService<SupplierAccountDto>,
        SearchableService<SupplierAccount, SupplierAccountDto> {

    List<SupplierAccountDto> searchByString(String nameFilter);

    List<SupplierAccountDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice);

    @Transactional
    default List<SupplierAccountDto> getAll(String typeOfInvoice) {
        return search((root, query, builder)
                -> builder.equal(root.get("typeOfInvoice"), TypeOfInvoice.valueOf(typeOfInvoice)));
    }

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}
