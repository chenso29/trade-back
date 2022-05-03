package src.main.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.models.entity.fias.FiasAddressModel;
import com.trade_accounting.repositories.fias.AddressDbRepository;
import com.trade_accounting.services.interfaces.fias.FiasDbService;
import com.trade_accounting.utils.mapper.fias.FiasAddressModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FiasDbServiceImpl implements FiasDbService {
    private final AddressDbRepository repository;

    private final FiasAddressModelMapper fiasAddressModelMapper;

    @Override
    public List<FiasAddressModelDto> getAll() {
        List<FiasAddressModel> all = repository.findAll();
        return all.stream().map(fiasAddressModelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FiasAddressModelDto getById(Long id) {
        FiasAddressModel model = repository.findById(id).orElse(null);
        return fiasAddressModelMapper.toDto(model);
    }

    @Override
    public FiasAddressModelDto create(FiasAddressModelDto dto) {
        FiasAddressModel model = repository.save(fiasAddressModelMapper.toModel(dto));
        return fiasAddressModelMapper.toDto(model);
    }

    @Override
    public void createAll(List<FiasAddressModelDto> dtoList) {
        List<FiasAddressModel> collect = dtoList.stream()
                .map(fiasAddressModelMapper::toModel).collect(Collectors.toList());
        repository.saveAll(collect);
    }

    @Override
    public FiasAddressModelDto update(FiasAddressModelDto dto) {
        return fiasAddressModelMapper.toDto(repository.save(fiasAddressModelMapper.toModel(dto)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FiasAddressModelDto> findAllByLevel(String level) {
        List<FiasAddressModel> allByLevel = repository.findAllByLevel(level);
        return allByLevel.stream().map(fiasAddressModelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FiasAddressModelDto> findAllByAoguid(String aoguid) {
        List<FiasAddressModel> byAoguid = repository.findAdressesByAoguid(aoguid);
        return byAoguid.stream().map(fiasAddressModelMapper::toDto).collect(Collectors.toList());
    }
}
