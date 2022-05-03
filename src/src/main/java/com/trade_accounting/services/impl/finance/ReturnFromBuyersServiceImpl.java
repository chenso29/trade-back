package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.ReturnFromBuyers;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import com.trade_accounting.repositories.finance.ReturnFromBuyersRepository;
import com.trade_accounting.services.interfaces.finance.ReturnFromBuyersService;
import com.trade_accounting.utils.mapper.finance.ReturnFromBuyersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnFromBuyersServiceImpl implements ReturnFromBuyersService {

    private final ReturnFromBuyersRepository returnFromBuyersRepository;
    private final ReturnFromBuyersMapper returnFromBuyersMapper;

    @Override
    public List<ReturnFromBuyersDto> getAll() {
        return returnFromBuyersRepository.getAll();
    }

    @Override
    public ReturnFromBuyersDto getById(Long id) {
        return returnFromBuyersRepository.getById(id);
    }

    @Override
    public ReturnFromBuyersDto create(ReturnFromBuyersDto dto) {
        ReturnFromBuyers returnFromBuyers = returnFromBuyersRepository.save(returnFromBuyersMapper.toModel(dto));
        dto.setId(returnFromBuyers.getId());
        return dto;
    }

    @Override
    public ReturnFromBuyersDto update(ReturnFromBuyersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        returnFromBuyersRepository.deleteById(id);
    }

    @Override
    public List<ReturnFromBuyersDto> search(Specification<ReturnFromBuyers> spec) {
        return executeSearch(returnFromBuyersRepository, returnFromBuyersMapper::toDto, spec);
    }
}
