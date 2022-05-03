package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.SupplierAccountProductsList;
import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SupplierAccountProductsListService extends AbstractService<SupplierAccountProductsListDto>,
        SearchableService<SupplierAccountProductsList, SupplierAccountProductsListDto> {

        @Transactional
        default List<SupplierAccountProductsListDto> searchBySupplierId(Long id){
            return search((root, query, builder) -> builder.equal(root.get("supplierAccount").get("id"), id));
        }

        @Transactional
        List<SupplierAccountProductsListDto> search(Specification<SupplierAccountProductsList> specification);
}
