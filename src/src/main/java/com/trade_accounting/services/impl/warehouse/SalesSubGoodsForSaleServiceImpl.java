package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import com.trade_accounting.repositories.warehouse.SalesSubGoodsForSaleRepository;
import com.trade_accounting.services.interfaces.warehouse.SalesSubGoodsForSaleService;
import com.trade_accounting.utils.mapper.warehouse.SalesSubGoodsForSaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesSubGoodsForSaleServiceImpl implements SalesSubGoodsForSaleService {

    private final SalesSubGoodsForSaleRepository salesSubGoodsForSaleRepository;

    private final SalesSubGoodsForSaleMapper salesSubGoodsForSaleMapper;

    @Override
    public List<SalesSubGoodsForSaleDto> getAll() {
        return salesSubGoodsForSaleRepository.findAll().stream()
                .map(salesSubGoodsForSaleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalesSubGoodsForSaleDto getById(Long id) {
        return salesSubGoodsForSaleMapper.toDto(
                salesSubGoodsForSaleRepository.findById(id).orElse(new SalesSubGoodsForSale())
        );
    }

    @Override
    public SalesSubGoodsForSaleDto create(SalesSubGoodsForSaleDto dto) {
        SalesSubGoodsForSale savedSalesSubGoodsForSale = salesSubGoodsForSaleRepository.save(
                salesSubGoodsForSaleMapper.toModel(dto)
        );
        dto.setId(savedSalesSubGoodsForSale.getId());
        return salesSubGoodsForSaleMapper.toDto(
                savedSalesSubGoodsForSale
        );
    }

    @Override
    public SalesSubGoodsForSaleDto update(SalesSubGoodsForSaleDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        salesSubGoodsForSaleRepository.deleteById(id);
    }

    @Override
    public List<SalesSubGoodsForSaleDto> search(Specification<SalesSubGoodsForSale> spec) {
        return executeSearch(salesSubGoodsForSaleRepository, salesSubGoodsForSaleMapper::toDto, spec);
    }

    @Override
    public List<SalesSubGoodsForSaleDto> searchByFilter(String search) {
        return salesSubGoodsForSaleRepository.searchString(search).stream()
                .map(salesSubGoodsForSaleMapper::toDto)
                .collect(Collectors.toList());

    }
}