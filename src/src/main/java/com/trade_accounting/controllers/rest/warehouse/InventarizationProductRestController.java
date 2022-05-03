package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import com.trade_accounting.repositories.warehouse.InventarizationProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.InventarizationProductService;
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
@Tag(name = "Inventarization Product Rest Controller", description = "CRUD  операции с товарами, которые инвентаризируем")
@Api(tags = "Inventarization Product Rest Controller")
@RequestMapping("/api/inventarization/product")
@RequiredArgsConstructor
public class InventarizationProductRestController {

    private final InventarizationProductService inventarizationProductService;
    private final CheckEntityService checkEntityService;
    private  final InventarizationProductRepository inventarizationProductRepository;
    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех инвентаризированных товаров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение инвентаризированных товаров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InventarizationProductDto>> getAll() {
        return ResponseEntity.ok(inventarizationProductService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение инвентаризированного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение инвентаризированного товара по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти инвентаризированный товар")
                                                        @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) inventarizationProductRepository, id);
        return ResponseEntity.ok(inventarizationProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового инвентаризированного товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризированный товар добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и инвентаризированный товар добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationProductDto> create(@ApiParam(name = "inventarizationProductDto",
            value = "DTO инвентаризованного товара, который необходимо создать")
                                                       @RequestBody InventarizationProductDto inventarizationProductDto) {

        return ResponseEntity.ok(inventarizationProductService.create(inventarizationProductDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об инвентаризированном товаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об инвентаризированном товаре добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об инвентаризированном товаре обновлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationProductDto> update(@ApiParam(name = "inventarizationProductDto",
            value = "DTO инвентаризованного товара, который необходимо обновить")
                                                       @RequestBody InventarizationProductDto inventarizationProductDto) {

        return ResponseEntity.ok(inventarizationProductService.update(inventarizationProductDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление инвентаризированного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Инвентаризированный товар удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InventarizationProductDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить инвентаризированный товар")
                                                           @PathVariable("id") Long id) {

        checkEntityService.checkExists((JpaRepository) inventarizationProductRepository, id);
        inventarizationProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
