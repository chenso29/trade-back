package src.main.java.com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.repositories.invoice.InvoicesStatusRepository;
import com.trade_accounting.services.interfaces.invoice.InvoicesStatusService;
import com.trade_accounting.utils.mapper.invoice.InvoicesStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoicesStatusServiceImpl implements InvoicesStatusService {

    private final InvoicesStatusRepository invoicesStatusRepository;

    private final InvoicesStatusMapper invoicesStatusMapper;


    @Override
    public List<InvoicesStatusDto> getAll() {
        return invoicesStatusRepository.findAll().stream()
                .map(invoicesStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoicesStatusDto getById(Long id) {
        InvoicesStatus invoicesStatus = invoicesStatusRepository.getOne(id);
        return invoicesStatusMapper.toDto(invoicesStatus);
    }

    @Override
    public InvoicesStatusDto create(InvoicesStatusDto dto) {
        InvoicesStatus invoicesStatus = invoicesStatusRepository.save(
                invoicesStatusMapper.toModel(dto)
        );
        dto.setId(invoicesStatus.getId());
        return invoicesStatusMapper.toDto(invoicesStatus);
    }

    @Override
    public InvoicesStatusDto update(InvoicesStatusDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        invoicesStatusRepository.deleteById(id);
    }

    @Override
    public InvoicesStatusDto getByName(String statusName) {
        return invoicesStatusMapper.toDto(
                invoicesStatusRepository.findByStatusName(statusName).orElse(new InvoicesStatus())
        );
    }
}
