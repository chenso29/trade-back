package src.main.java.com.trade_accounting.services.interfaces.invoice;


import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvoiceProductService extends AbstractService<InvoiceProductDto>,
        SearchableService<InvoiceProduct, InvoiceProductDto> {

    @Transactional
    default List<InvoiceProductDto> searchByInvoiceId(Long id) {
        return search((root, query, builder) -> builder.equal(root.get("invoice").get("id"), id));
    }

    @Transactional
    List<InvoiceProductDto> search(Specification<InvoiceProduct> specification);

    List<InvoiceProductDto> getAllByProductId(Long id);
}
