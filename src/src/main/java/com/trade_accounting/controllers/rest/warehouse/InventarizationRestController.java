package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.repositories.warehouse.InventarizationRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.InventarizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
@Tag(name = "Inventarization Rest Controller", description = "CRUD операции с инвентаризацией")
@Api(tags = "Inventarization Rest Controller")
@RequestMapping("/api/inventarization")
@RequiredArgsConstructor
public class InventarizationRestController {

    private final InventarizationService inventarizationService;
    private final CheckEntityService checkEntityService;
    private final InventarizationRepository inventarizationRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех инвентаризаций")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка инвентаризаций"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InventarizationDto>> getAll() {
        return ResponseEntity.ok(inventarizationService.getAll());
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка инвентаризаций по заданным параметрам")
    public ResponseEntity<List<InventarizationDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyId", spec = Like.class),
                    @Spec(path = "warehouse.name", params = "warehouseId", spec = Like.class),
                    @Spec(path = "comment", params = "comment", spec = Like.class),
                    @Spec(path = "status", params = "sent", spec = Equal.class),
                    @Spec(path = "status", params = "print", spec = Equal.class),
            }) Specification<Inventarization> spec) {
        return ResponseEntity.ok(inventarizationService.search(spec));
    }

    @GetMapping("/search/{search}")
    @ApiOperation(value = "search", notes = "Получение списка инвентаризаций по номеру или комментарию")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка инвентаризаций по номеру или комментарию"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InventarizationDto>> search(@ApiParam(name = "search",
            value = "Переданный в URL search, по которому необходимо найти приемку")
                                                           @PathVariable(name = "search") String search) {
        List<InventarizationDto> inventarizationDtoList = inventarizationService.search(search);
        return ResponseEntity.ok(inventarizationDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение конкретной инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение инвентаризации"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти инвентаризацию")
                                                      @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) inventarizationRepository, id);

        return ResponseEntity.ok(inventarizationService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация создана"),
            @ApiResponse(code = 201, message = "Запрос принят и инвентаризация добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> create(@ApiParam(name = "inventarizationDto",
            value = "DTO инвентаризации, которое необходимо создать")
                                                     @RequestBody InventarizationDto inventarizationDto) {

        return ResponseEntity.ok(inventarizationService.create(inventarizationDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об инвентаризации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об инвентаризации обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об инвентаризации обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> update(@ApiParam(name = "inventarizationDto",
            value = "DTO инвентаризации, которое необходимо обновить")
                                                     @RequestBody InventarizationDto inventarizationDto) {

        return ResponseEntity.ok(inventarizationService.update((inventarizationDto)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление инвентаризации по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить инвентаризацию")
                                                         @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) inventarizationRepository, id);
        inventarizationService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину инвентаризации по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация перенесена в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить инвентаризацию")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) inventarizationRepository, id);
        inventarizationService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление инвентаризации по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризация восстановленна"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить инвентаризацию")
                                                               @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) inventarizationRepository, id);
        inventarizationService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }

}
