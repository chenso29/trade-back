package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.repositories.warehouse.SerialNumbersRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.SerialNumbersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
@Tag(name = "SerialNumbers Rest Controller", description = "CRUD операции с серийными номерами")
@Api(tags = "SerialNumbers Rest Controller")
@RequestMapping("/api/serialnumbers")
@RequiredArgsConstructor
public class SerialNumbersRestController {

    private final SerialNumbersRepository serialNumbersRepository;
    private final SerialNumbersService serialNumbersService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех серийные номера")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех серийных номеров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SerialNumbersDto>> getAll() {
        return ResponseEntity.ok(serialNumbersService.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает серийный номер по ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Серийные номера найдены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SerialNumbersDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти серийный номер",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) serialNumbersRepository, id);
        return ResponseEntity.ok(serialNumbersService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает серийные номера на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Серийный номер успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SerialNumbersDto> create(@ApiParam(name = "revenueDto",
            value = "DTO серийного номера, которую необходимо создать") @RequestBody SerialNumbersDto serialNumbersDto) {
        return ResponseEntity.ok().body(serialNumbersService.create(serialNumbersDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет серийный номер на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Серийный номер успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SerialNumbersDto> update(@ApiParam(name = "revenueDto",
            value = "DTO серийного номера, который необходимо обновить") @RequestBody SerialNumbersDto serialNumbersDto) {
        checkEntityService.checkExists((JpaRepository) serialNumbersRepository, serialNumbersDto.getId());
        return ResponseEntity.ok().body(serialNumbersService.update(serialNumbersDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет серийный номер на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Серийный номер успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Long> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить серийный номер",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        serialNumbersService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка серийных номеров по фильтру")
    public ResponseEntity<List<SerialNumbersDto>> searchByFilter(
            @And({
                    @Spec(path = "code", params = "code", spec = Equal.class),
                    @Spec(path = "vendorCode", params = "vendorCode", spec = Equal.class),
                    @Spec(path = "product.id", params = "productId", spec = Equal.class),
                    @Spec(path = "warehouse.id", params = "warehouseId", spec = Equal.class),
                    @Spec(path = "typeDocument", params = "typeDocument", spec = Equal.class),
                    @Spec(path = "documentNumber", params = "documentNumber", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = Equal.class),
            }) Specification<SerialNumbers> spec) {
        return ResponseEntity.ok(serialNumbersService.search(spec));
    }
}
