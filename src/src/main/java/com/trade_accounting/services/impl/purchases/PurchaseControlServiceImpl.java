package src.main.java.com.trade_accounting.services.impl.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.repositories.purchases.PurchaseControlRepository;
import com.trade_accounting.services.interfaces.purchases.PurchaseControlService;
import com.trade_accounting.utils.mapper.purchases.PurchaseControlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseControlServiceImpl implements PurchaseControlService {
    private final PurchaseControlRepository purchaseControlRepository;
    private final PurchaseControlMapper purchaseControlMapper;


    @Override
    public List<PurchaseControlDto> getAll() {
        return purchaseControlRepository.findAll().stream()
                .map(purchaseControlMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseControlDto getById(Long id) {
        return purchaseControlMapper.toDto(purchaseControlRepository.getOne(id));
    }

    @Override
    public PurchaseControlDto create(PurchaseControlDto dto) {
        PurchaseControl purchaseControl = purchaseControlMapper.toModel(dto);
        purchaseControlRepository.save(purchaseControl);
        return dto;
    }

    @Override
    public PurchaseControlDto update(PurchaseControlDto dto) {
        PurchaseControl purchaseControl = purchaseControlMapper.toModel(dto);
        purchaseControlRepository.save(purchaseControl);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        purchaseControlRepository.deleteById(id);
    }

    @Override
    public List<PurchaseControlDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<PurchaseControl> allStore = purchaseControlRepository.findAll();
            return allStore.stream().map(purchaseControlMapper::toDto).collect(Collectors.toList());
        } else {
            List<PurchaseControl> list = purchaseControlRepository.search(searchTerm);
            return list.stream().map(purchaseControlMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<PurchaseControlDto> search(Specification<PurchaseControl> spec) {
        return executeSearch(purchaseControlRepository, purchaseControlMapper::toDto, spec);
    }
}
