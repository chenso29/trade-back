package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import com.trade_accounting.models.dto.production.TechnicalCardProductionDto;
import com.trade_accounting.repositories.production.TechnicalCardProductionRepository;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
import com.trade_accounting.services.interfaces.production.TechnicalCardProductionService;
import com.trade_accounting.utils.mapper.warehouse.ProductMapper;
import com.trade_accounting.utils.mapper.production.TechnicalCardProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalCardProductionServiceImpl implements TechnicalCardProductionService {
    private final TechnicalCardProductionRepository cardProductionRepository;
    private final TechnicalCardProductionMapper cardProductionMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public List<TechnicalCardProductionDto> getAll() {
        return cardProductionRepository.findAll().stream()
                .map(cardProductionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TechnicalCardProductionDto getById(Long id) {
        return cardProductionMapper.toDto(cardProductionRepository.getOne(id));
    }

    @Override
    public TechnicalCardProductionDto create(TechnicalCardProductionDto dto) {
        TechnicalCardProduction technicalCardProduction = cardProductionMapper.toModel(dto);

        technicalCardProduction.setProduct(
                productMapper.toModel(productService.getById(dto.getProductId()))
        );
        return cardProductionMapper.toDto(cardProductionRepository.save(technicalCardProduction));
    }

    @Override
    public TechnicalCardProductionDto update(TechnicalCardProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        cardProductionRepository.deleteById(id);
    }


    @Override
    public List<TechnicalCardProduction> finaAllById(List<Long> id) {
        return cardProductionRepository.findAllById(id);
    }
}
