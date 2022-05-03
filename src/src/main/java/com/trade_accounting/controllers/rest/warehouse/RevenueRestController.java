package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.RevenueDto;
import com.trade_accounting.repositories.warehouse.RevenueRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.RevenueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Revenue Rest Controller", description = "CRUD операции с оборотами")
@Api(tags = "Revenue Rest Controller")
@RequestMapping("/api/revenue")
@RequiredArgsConstructor
public class RevenueRestController {

	private final RevenueService revenueService;
	private final CheckEntityService checkEntityService;
	private final RevenueRepository revenueRepository;

	@ApiOperation(value = "getAll", notes = "Возвращает список всех оборотов")
	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка всех оборотов"),
			@ApiResponse(code = 404, message = "Данный контроллер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<RevenueDto>> getAll() {
		return ResponseEntity.ok(revenueService.getAll());
	}

	@ApiOperation(value = "getById", notes = "Возвращает обороты по ID")
	@GetMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Обороты найдены"),
			@ApiResponse(code = 404, message = "Данный контроллер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<RevenueDto> getById(@ApiParam(
			name = "id",
			type = "Long",
			value = "ID переданный в URL по которому необходимо найти обороты",
			example = "1",
			required = true) @PathVariable(name = "id") Long id) {
		checkEntityService.checkExists((JpaRepository) revenueRepository, id);
		return ResponseEntity.ok(revenueService.getById(id));
	}

	@ApiOperation(value = "create", notes = "Создает обороты на основе переданных данных")
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Обороты успешно добавлена"),
			@ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
			@ApiResponse(code = 404, message = "Данный контроллер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<RevenueDto> create(@ApiParam(name = "revenueDto",
			value = "DTO оборотов, которую необходимо создать") @RequestBody RevenueDto revenueDto) {
		return ResponseEntity.ok().body(revenueService.create(revenueDto));
	}

	@ApiOperation(value = "update", notes = "Обновляет остаток на основе переданных данных")
	@PutMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Обороты успешно обновлены"),
			@ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
			@ApiResponse(code = 404, message = "Данный контроллер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<RevenueDto> update(@ApiParam(name = "revenueDto",
			value = "DTO остатка, который необходимо обновить") @RequestBody RevenueDto revenueDto) {
		checkEntityService.checkExists((JpaRepository) revenueRepository, revenueDto.getId());
		return ResponseEntity.ok().body(revenueService.update(revenueDto));
	}

	@ApiOperation(value = "deleteById", notes = "Удаляет обороты на основе переданного ID")
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Обороты успешно удалена"),
			@ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<Long> deleteById(@ApiParam(
			name = "id",
			type = "Long",
			value = "ID переданный в URL по которому необходимо удалить обороты",
			example = "1",
			required = true) @PathVariable(name = "id") Long id) {
		revenueService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
