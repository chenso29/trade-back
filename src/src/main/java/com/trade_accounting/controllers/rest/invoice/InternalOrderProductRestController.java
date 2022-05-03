package src.main.java.com.trade_accounting.controllers.rest.invoice;

import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
import com.trade_accounting.repositories.invoice.InternalOrderProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.invoice.InternalOrderProductService;
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

/**
 * @author Pavel Andrusov
 * @since 19.07.2021
 */

@RestController
@Tag(name = "Internal Order Product Rest Controller", description = "CRUD  операции с товарами внутренних заказов")
@Api(tags = "Internal Order Product Rest Controller")
@RequestMapping("/api/internalorder/product")
@RequiredArgsConstructor
public class InternalOrderProductRestController {
    private final InternalOrderProductService internalOrderProductService;
    private final InternalOrderProductRepository internalOrderProductRepository;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех товаров внутренних заказов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка товаров внутренних заказов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InternalOrderProductsDto>> getAll() {
        return ResponseEntity.ok(internalOrderProductService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение товара внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение товара внутреннего заказа по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderProductsDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти товар внутреннего заказа")
                                                    @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) internalOrderProductRepository, id);

        return ResponseEntity.ok(internalOrderProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового товара внутреннего заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар внутреннего заказа создан"),
            @ApiResponse(code = 201, message = "Запрос принят и товар внутреннего заказа добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderProductsDto> create(@ApiParam(name = "internalOrderProductsDto",
            value = "DTO товара внутреннего заказа, которое необходимо создать")
                                                   @RequestBody InternalOrderProductsDto internalOrderProductsDto) {
        return ResponseEntity.ok(internalOrderProductService.create(internalOrderProductsDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о товаре внутреннего заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о товаре внутреннего заказа обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о товаре внутреннего заказа обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderProductsDto> update(@ApiParam(name = "internalOrderProductsDto",
            value = "DTO товара внутреннего заказа, которую необходимо обновить")
                                                   @RequestBody InternalOrderProductsDto internalOrderProductsDto) {
        return ResponseEntity.ok(internalOrderProductService.update(internalOrderProductsDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление товара внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар внутреннего заказа удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InternalOrderProductsDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить товар внутреннего заказа")
                                                       @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) internalOrderProductRepository, id);
        internalOrderProductService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
