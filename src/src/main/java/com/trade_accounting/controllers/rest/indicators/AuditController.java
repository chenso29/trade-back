package src.main.java.com.trade_accounting.controllers.rest.indicators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.services.interfaces.indicators.audit.AuditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditController {

	private final AuditService auditService;

	@ApiOperation(value = "getAll", notes = "Возвращает список всех аудитов")
	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка всех аудитов"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> getAll() {
		return ResponseEntity.ok(auditService.getAll());
	}

	@ApiOperation(value = "searchByEmployeeOrTime", notes = "Получение списка аудитов по заданным параметрам - сотрудник или время")
	@GetMapping("/searchByStringEmployeeOrTime")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка аудитов по заданным параметрам"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> searchByString(@RequestParam("search") String search) {
		return ResponseEntity.ok(auditService.searchByString(search));
	}

	@ApiOperation(value = "searchByFilter", notes = "Получение списка аудитов по фильтру")
	@GetMapping("/searchByFilter")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка всех аудитов по фильтру"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> searchByFilter(
			@And({
					@Spec(path = "description", params = "description", spec = Equal.class),
					@Spec(path = "date", params = "date", spec = Equal.class),
					@Spec(path = "employee.id", params = "employeeId", spec = Equal.class),
			}) Specification<Audit> spec) {
		return ResponseEntity.ok(auditService.search(spec));
	}
}
