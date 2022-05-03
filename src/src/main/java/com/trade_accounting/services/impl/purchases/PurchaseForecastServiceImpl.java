package src.main.java.com.trade_accounting.services.impl.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import com.trade_accounting.repositories.purchases.PurchaseForecastRepository;
import com.trade_accounting.services.interfaces.purchases.PurchaseForecastService;
import com.trade_accounting.utils.mapper.purchases.PurchaseForecastMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseForecastServiceImpl implements PurchaseForecastService {
    private final PurchaseForecastRepository purchaseForecastRepository;
    private final PurchaseForecastMapper purchaseForecastMapper;

    @Override
    public List<PurchaseForecastDto> getAll() {
        return purchaseForecastRepository.findAll().stream()
                .map(purchaseForecastMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseForecastDto getById(Long id) {
        return purchaseForecastMapper.toDto(purchaseForecastRepository.getOne(id));
    }

    @Override
    public PurchaseForecastDto create(PurchaseForecastDto dto) {
        PurchaseForecast purchaseForecast = purchaseForecastMapper.toModel(dto);
        purchaseForecastRepository.save(purchaseForecast);
        return dto;
    }

    @Override
    public PurchaseForecastDto update(PurchaseForecastDto dto) {
        PurchaseForecast purchaseForecast = purchaseForecastMapper.toModel(dto);
        purchaseForecastRepository.save(purchaseForecast);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        purchaseForecastRepository.deleteById(id);
    }
}
