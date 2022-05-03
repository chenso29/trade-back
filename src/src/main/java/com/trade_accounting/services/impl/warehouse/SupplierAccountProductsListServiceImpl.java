package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SupplierAccountProductsList;
import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.SupplierAccountProductsListRepository;
import com.trade_accounting.services.interfaces.warehouse.SupplierAccountProductsListService;
import com.trade_accounting.services.interfaces.company.SupplierAccountService;
import com.trade_accounting.utils.mapper.company.SupplierAccountMapper;
import com.trade_accounting.utils.mapper.warehouse.SupplierAccountProductsListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierAccountProductsListServiceImpl implements SupplierAccountProductsListService {
    private final SupplierAccountProductsListRepository supplierAccountProductsListRepository;
    private final SupplierAccountProductsListMapper mapper;
    private final ProductRepository productRepository;
    private final SupplierAccountService supplierAccountService;
    private final SupplierAccountMapper supplierAccountMapper;

    @Override
    public List<SupplierAccountProductsListDto> getAll() {
        return supplierAccountProductsListRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SupplierAccountProductsListDto getById(Long id) {
        Optional<SupplierAccountProductsList> supplierAccountProductsListOptional = supplierAccountProductsListRepository.findById(id);
        return mapper.toDto(supplierAccountProductsListOptional.orElse(new SupplierAccountProductsList()));
    }

    @Override
    public SupplierAccountProductsListDto create(SupplierAccountProductsListDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public SupplierAccountProductsListDto update(SupplierAccountProductsListDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountProductsListRepository.deleteById(id);
    }

    @Override
    public List<SupplierAccountProductsListDto> search(Specification<SupplierAccountProductsList> specification) {
        return executeSearch(supplierAccountProductsListRepository, mapper::toDto, specification);
    }

    private SupplierAccountProductsListDto saveOrUpdate(SupplierAccountProductsListDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        SupplierAccountProductsList supplierAccountProductsList = mapper.toModel(dto);
        supplierAccountProductsList.setSupplierAccount(supplierAccountMapper.toModel(supplierAccountService.getById(dto.getSupplierAccountId())));
        supplierAccountProductsList.setProduct(product.orElse(null));
        return mapper.toDto(supplierAccountProductsListRepository.save(supplierAccountProductsList));
    }
}
