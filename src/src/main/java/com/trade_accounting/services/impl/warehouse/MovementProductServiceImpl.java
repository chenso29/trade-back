package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import com.trade_accounting.repositories.warehouse.MovementProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.warehouse.MovementProductService;
import com.trade_accounting.utils.mapper.warehouse.MovementProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovementProductServiceImpl implements MovementProductService {

    private final MovementProductRepository movementProductRepository;
    private final ProductRepository productRepository;
    private final MovementProductMapper movementProductMapper;

    @Override
    public List<MovementProductDto> getAll() {
        return movementProductRepository.findAll().stream()
                .map(movementProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovementProductDto getById(Long id) {
        Optional<MovementProduct> movementProduct = movementProductRepository.findById(id);
        return movementProductMapper.toDto(movementProduct.orElse(new MovementProduct()));
    }

    @Override
    public MovementProductDto create(MovementProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public MovementProductDto update(MovementProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        movementProductRepository.deleteById(id);
    }

    private MovementProductDto saveOrUpdate(MovementProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        MovementProduct movementProduct = movementProductMapper.toModel(dto);
        movementProduct.setProduct(product.orElse(null));

        return movementProductMapper.toDto(movementProductRepository.save(movementProduct));
    }
}
