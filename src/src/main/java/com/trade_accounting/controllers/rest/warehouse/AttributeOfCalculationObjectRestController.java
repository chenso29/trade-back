package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.AttributeOfCalculationObjectDto;
import com.trade_accounting.repositories.warehouse.AttributeOfCalculationObjectRepository;
import com.trade_accounting.services.interfaces.warehouse.AttributeOfCalculationObjectService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Tag(name = "Attribute Of Calculation Object Rest Controller", description = "CRUD операции с объектами")
@Api(tags = "Attribute Of Calculation Object Rest Controller")
@RequestMapping("/api/attribute/calculation/object")
@RequiredArgsConstructor
public class AttributeOfCalculationObjectRestController {

    private final AttributeOfCalculationObjectService attributeOfCalculationObjectService;
    private final CheckEntityService checkEntityService;
    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех Attribute Of Calculation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка Attribute Of Calculation"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<AttributeOfCalculationObjectDto>> getAll() {
        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjectDtos = attributeOfCalculationObjectService.getAll();
        return ResponseEntity.ok(attributeOfCalculationObjectDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение Attribute Of Calculation по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Attribute Of Calculation найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AttributeOfCalculationObjectDto> getById(@PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) attributeOfCalculationObjectRepository, id);
        return ResponseEntity.ok(attributeOfCalculationObjectService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового Attribute Of Calculation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Attribute Of Calculation создан"),
            @ApiResponse(code = 201, message = "Запрос принят и Attribute Of Calculation добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto) {
        return ResponseEntity.ok().body(attributeOfCalculationObjectService.create(attributeOfCalculationObjectDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об Attribute Of Calculation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об Attribute Of Calculation обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об Attribute Of Calculation обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto) {
        return ResponseEntity.ok().body(attributeOfCalculationObjectService.update(attributeOfCalculationObjectDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление объекта по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Attribute Of Calculation удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        attributeOfCalculationObjectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
