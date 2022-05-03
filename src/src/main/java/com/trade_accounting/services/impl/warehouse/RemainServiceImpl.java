package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Remain;
import com.trade_accounting.models.dto.warehouse.RemainDto;
import com.trade_accounting.repositories.warehouse.RemainRepository;
import com.trade_accounting.services.interfaces.warehouse.RemainService;
import com.trade_accounting.utils.mapper.warehouse.RemainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RemainServiceImpl implements RemainService {

    private final RemainRepository remainRepository;

    private final RemainMapper remainMapper;

    @Override
    public List<RemainDto> getAll() {

      final List<RemainDto> collect = remainRepository.findAll().stream()
              .map(remainMapper::toDto)
              .collect(Collectors.toList());
      return collect;
    }

    @Override
    public RemainDto getById(Long id) {
        return remainMapper.toDto(remainRepository.getOne(id));
    }

    @Override
    public RemainDto create(RemainDto dto) {
        Remain remain = remainRepository.save(remainMapper.toModel(dto));
        dto.setId(remain.getId());
        return remainMapper.toDto(remain);

    }

    @Override
    public RemainDto update(RemainDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        remainRepository.deleteById(id);
    }
}
