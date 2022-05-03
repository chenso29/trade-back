package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.services.interfaces.warehouse.AcceptanceProductionService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
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
@Tag(name = "Acceptance Production Rest Controller", description = "CRUD  операции с приемкой товара")
@Api(tags = "Acceptance Production Rest Controller")
@RequestMapping("/api/acceptance/product")
@RequiredArgsConstructor
public class AcceptanceProductionRestController {
    private final AcceptanceProductionService acceptanceProductionService;
    private final CheckEntityService checkEntityService;
    private final AcceptanceProductionRepository acceptanceProductionRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех приемок товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка приемок товара"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<AcceptanceProductionDto>> getAll() {
        return ResponseEntity.ok(acceptanceProductionService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списка всех приемок товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка приемок товара"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceProductionDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти приемку")
                                                 @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceProductionRepository, id);

        return ResponseEntity.ok(acceptanceProductionService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой приемки товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка товара создана"),
            @ApiResponse(code = 201, message = "Запрос принят и приемка товара добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceProductionDto> create(@ApiParam(name = "acceptanceProductionDto",
            value = "DTO приемки товара, которое необходимо создать")
                                                @RequestBody AcceptanceProductionDto acceptanceProductionDto) {
        return ResponseEntity.ok(acceptanceProductionService.create(acceptanceProductionDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о приемки товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о приемки товара обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные оприемке товара обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceProductionDto> update(@ApiParam(name = "acceptanceProductionDto",
            value = "DTO приемки, которую необходимо обновить")
                                                @RequestBody AcceptanceProductionDto acceptanceProductionDto) {
        return ResponseEntity.ok(acceptanceProductionService.update(acceptanceProductionDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление приемки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceProductionDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить приемку")
                                                    @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceProductionRepository, id);
        acceptanceProductionService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
