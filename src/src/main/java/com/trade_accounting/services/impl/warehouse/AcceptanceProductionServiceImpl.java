package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.warehouse.AcceptanceProductionService;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceProductionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceProductionServiceImpl implements AcceptanceProductionService {

    private final AcceptanceProductionRepository acceptanceProductionRepository;

    private final ProductRepository productRepository;

    private final AcceptanceProductionMapper acceptanceProductionMapper;

    public AcceptanceProductionServiceImpl(AcceptanceProductionRepository acceptanceProductionRepository,
                                           ProductRepository productRepository,
                                           AcceptanceProductionMapper acceptanceProductionMapper) {
        this.acceptanceProductionRepository = acceptanceProductionRepository;
        this.productRepository = productRepository;
        this.acceptanceProductionMapper = acceptanceProductionMapper;
    }

    @Override
    public List<AcceptanceProductionDto> getAll() {
        return acceptanceProductionRepository.findAll().stream()
                .map(acceptanceProductionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcceptanceProductionDto getById(Long id) {
        return acceptanceProductionMapper.toDto(
                acceptanceProductionRepository.getOne(id)
        );
    }

    @Override
    public AcceptanceProductionDto create(@NotNull AcceptanceProductionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public AcceptanceProductionDto update(AcceptanceProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        acceptanceProductionRepository.deleteById(id);
    }

    private AcceptanceProductionDto saveOrUpdate(AcceptanceProductionDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());

        AcceptanceProduction acceptanceProduction = acceptanceProductionMapper.toModel(dto);

        acceptanceProduction.setProduct(product.orElse(null));

        return acceptanceProductionMapper.toDto(acceptanceProductionRepository.save(acceptanceProduction));
    }
}
