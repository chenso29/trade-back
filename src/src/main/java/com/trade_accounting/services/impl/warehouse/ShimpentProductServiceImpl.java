package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ShipmentProduct;
import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.services.interfaces.warehouse.ShipmentProductService;
import com.trade_accounting.utils.mapper.warehouse.ShipmentProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShimpentProductServiceImpl implements ShipmentProductService {

    private final ShipmentProductRepository shipmentProductRepository;
    private final ProductRepository productRepository;
    private final ShipmentProductMapper shipmentProductMapper;

    @Override
    public List<ShipmentProductDto> getAll() {
        return shipmentProductRepository.findAll().stream()
                .map(shipmentProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentProductDto getById(Long id) {
        return shipmentProductMapper.toDto(shipmentProductRepository.getOne(id));
    }

    @Override
    public ShipmentProductDto create(ShipmentProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public ShipmentProductDto update(ShipmentProductDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        shipmentProductRepository.deleteById(id);
    }

    private ShipmentProductDto saveOrUpdate(ShipmentProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        ShipmentProduct shipmentProduct = shipmentProductMapper.toModel(dto);
        shipmentProduct.setProduct(product.orElse(null));
        return shipmentProductMapper.toDto(shipmentProductRepository.save(shipmentProduct));
    }
}
