package src.main.java.com.trade_accounting.services.interfaces.indicators.audit;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface AuditService extends SearchableService<Audit, AuditDto> {

	List<AuditDto> getAll();
	
	AuditDto getById(Long id);
	
	AuditDto create(AuditDto auditDto);
	
	AuditDto update(AuditDto auditDto);
	
	void deleteById(Long id);

	List<AuditDto> searchByFilter(String search);

	List<AuditDto> searchByString(String text);
}
