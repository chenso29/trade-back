package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.dto.company.TaxSystemDto;
import com.trade_accounting.repositories.company.TaxSystemRepository;
import com.trade_accounting.services.interfaces.company.TaxSystemService;
import com.trade_accounting.utils.mapper.company.TaxSystemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class TaxSystemServiceImpl implements TaxSystemService {

    private final TaxSystemRepository taxSystemRepository;
    private final TaxSystemMapper taxSystemMapper;

    @Override
    public List<TaxSystemDto> getAll() {
        return taxSystemRepository.findAll().stream()
                .map(taxSystemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaxSystemDto getById(Long id) {
        return taxSystemMapper.toDto(
                taxSystemRepository.findById(id).orElse(new TaxSystem()));
    }

    @Override
    public TaxSystemDto create(TaxSystemDto taxSystemDto) {
        TaxSystem taxSystem = taxSystemMapper.toModel(taxSystemDto);
        taxSystemDto.setId(taxSystem.getId());
        return taxSystemMapper.toDto(
                taxSystemRepository.save(taxSystem));
    }


    @Override
    public TaxSystemDto update(TaxSystemDto taxSystemDto) {
        return create(taxSystemDto);

    }

    @Override
    public void deleteById(Long id) {
        taxSystemRepository.deleteById(id);
    }
}
