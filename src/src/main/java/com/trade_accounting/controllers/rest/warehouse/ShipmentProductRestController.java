package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.ShipmentProductDto;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.ShipmentProductService;
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
@Tag(name = "ShipmentProduct Rest Controller", description = "CRUD  операции со отгруженными продуктами")
@Api(tags = "ShipmentProduct Rest Controller")
@RequestMapping("/api/shipmentproduct")
@RequiredArgsConstructor
public class ShipmentProductRestController {

    private final ShipmentProductRepository shipmentProductRepository;
    private final ShipmentProductService shipmentProductService;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех отгруженных продуктов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка отгруженных продуктов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ShipmentProductDto>> getAll() {
        List<ShipmentProductDto> shipmentProductDtos;
        shipmentProductDtos = shipmentProductService.getAll();
        return ResponseEntity.ok(shipmentProductDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение отгруженного продукта по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отгруженный продукт найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ShipmentProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти отгруженный продукт")
                                                  @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) shipmentProductRepository,id);
        return ResponseEntity.ok(shipmentProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового отгруженного продукта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отгруженный продукт создан"),
            @ApiResponse(code = 201, message = "Запрос принят и списание добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ShipmentProductDto> create(@ApiParam(name = "shipmentDto", value = "DTO отгруженного продукта, которого необходимо создать")
                                                 @RequestBody ShipmentProductDto shipmentProductDto) {
        return ResponseEntity.ok().body(shipmentProductService.create(shipmentProductDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об отгруженном продукте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об отгруженном продукте обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о списании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "shipmentDto", value = "DTO отгруженного продукта, которого необходимо обновить")
                                    @RequestBody ShipmentProductDto shipmentProductDto) {
        return ResponseEntity.ok().body(shipmentProductService.update(shipmentProductDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление отгруженного продукта по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отгруженный продукт удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить отгруженный продукт")
                                        @PathVariable(name = "id") Long id) {
        shipmentProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

