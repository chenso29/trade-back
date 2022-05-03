package src.main.java.com.trade_accounting.services.impl.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import com.trade_accounting.repositories.purchases.PurchaseCurrentBalanceRepository;
import com.trade_accounting.services.interfaces.purchases.PurchaseCurrentBalanceService;
import com.trade_accounting.utils.mapper.purchases.PurchaseCurrentBalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseCurrentBalanceServiceImpl implements PurchaseCurrentBalanceService {
    private final PurchaseCurrentBalanceRepository purchaseCurrentBalanceRepository;
    private final PurchaseCurrentBalanceMapper purchaseCurrentBalanceMapper;
    @Override
    public List<PurchaseCurrentBalanceDto> getAll() {
        return purchaseCurrentBalanceRepository.findAll().stream()
                .map(purchaseCurrentBalanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseCurrentBalanceDto getById(Long id) {
        return purchaseCurrentBalanceMapper.toDto(purchaseCurrentBalanceRepository.getOne(id));
    }

    @Override
    public PurchaseCurrentBalanceDto create(PurchaseCurrentBalanceDto dto) {
        PurchaseCurrentBalance purchaseCurrentBalance = purchaseCurrentBalanceMapper.toModel(dto);
        purchaseCurrentBalanceRepository.save(purchaseCurrentBalance);
        return dto;
    }

    @Override
    public PurchaseCurrentBalanceDto update(PurchaseCurrentBalanceDto dto) {
        PurchaseCurrentBalance purchaseCurrentBalance = purchaseCurrentBalanceMapper.toModel(dto);
        purchaseCurrentBalanceRepository.save(purchaseCurrentBalance);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
purchaseCurrentBalanceRepository.deleteById(id);
    }
}
