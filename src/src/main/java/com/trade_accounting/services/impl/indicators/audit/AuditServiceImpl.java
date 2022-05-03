package src.main.java.com.trade_accounting.services.impl.indicators.audit;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.repositories.indicators.AuditRepository;
import com.trade_accounting.services.interfaces.indicators.audit.AuditService;
import com.trade_accounting.utils.mapper.indicators.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
	
	private final AuditRepository auditRepository;
	private  final AuditMapper auditMapper;
	
	@Override
	public List<AuditDto> getAll() {
		return auditRepository.findAll()
				.stream().map(auditMapper::auditToAuditDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public AuditDto getById(Long id) {
		return auditMapper.auditToAuditDto(auditRepository.getOne(id));
	}
	
	@Override
	public AuditDto create(AuditDto auditDto) {
		return saveOrUpdate(auditDto);
	}
	
	@Override
	public AuditDto update(AuditDto auditDto) {
		return saveOrUpdate(auditDto);
	}
	
	private AuditDto saveOrUpdate(AuditDto auditDto) {
		return auditMapper.auditToAuditDto(auditRepository.save(auditMapper.auditDtoToAudit(auditDto)));
	}
	
	@Override
	public void deleteById(Long id) {
		auditRepository.deleteById(id);
	}

	@Override
	public List<AuditDto> search(Specification<Audit> spec) {
		return executeSearch(auditRepository, auditMapper::auditToAuditDto, spec);
	}

	@Override
	public List<AuditDto> searchByString(String text) {
		return auditRepository.getBySearch(text).stream()
				.map(auditMapper::auditToAuditDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<AuditDto> searchByFilter(String search) {
		return auditRepository.searchString(search).stream()
				.map(auditMapper::auditToAuditDto)
				.collect(Collectors.toList());
	}
}
