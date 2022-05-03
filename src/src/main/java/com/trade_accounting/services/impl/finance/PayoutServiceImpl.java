package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.Payout;
import com.trade_accounting.models.dto.finance.PayoutDto;
import com.trade_accounting.repositories.finance.PayoutRepository;
import com.trade_accounting.services.interfaces.finance.PayoutService;
import com.trade_accounting.utils.mapper.finance.PayoutMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PayoutServiceImpl implements PayoutService {

    private final PayoutRepository payoutRepository;

    private final PayoutMapper payoutMapper;


    @Override
    public List<PayoutDto> getAll() {
        return payoutRepository.findAll().stream()
                .map(payoutMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayoutDto getById(Long id) {
        return payoutMapper.toDto(payoutRepository.getOne(id));
    }

    @Override
    public PayoutDto create(PayoutDto payoutDto) {
        Payout payout = payoutRepository.save(payoutMapper.toModel(payoutDto));
        if (payoutDto.getId() == null) {
            payoutDto.setId(payout.getId());
        }
        return payoutMapper.toDto(payout);
    }

    @Override
    public PayoutDto update(PayoutDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        payoutRepository.deleteById(id);
    }

    @Override
    public List<PayoutDto> getAllByParametrs(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<Payout> all = payoutRepository.findAll();
            return all.stream().map(payoutMapper::toDto).collect(Collectors.toList());
        } else {
            List<Payout> list = payoutRepository.search(searchTerm);
            return list.stream().map(payoutMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<PayoutDto> search(Specification<Payout> spec) {
        return executeSearch(payoutRepository, payoutMapper::toDto, spec);
    }
}
