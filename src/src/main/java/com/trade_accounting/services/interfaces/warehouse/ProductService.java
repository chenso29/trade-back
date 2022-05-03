package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService extends AbstractService<ProductDto>, SearchableService<Product, ProductDto> {

    void deleteById(Long id);

    @Transactional
    default List<ProductDto> getAllByProductGroupId(Long id) {
        return search((root, query, builder) ->
                builder.equal(root.get("productGroup").get("id"), id));
    }

    @Transactional
    default List<ProductDto> getAllByContractorId(Long id) {
        return search((root, query, builder) ->
                builder.equal(root.get("contractor").get("id"), id));
    }

    PageDto<ProductDto> search(Specification<Product> specification, Pageable page);

    List<ProductDto> search(String value);
}