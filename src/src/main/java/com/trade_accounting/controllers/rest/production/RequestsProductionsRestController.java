package src.main.java.com.trade_accounting.controllers.rest.production;


import com.trade_accounting.models.dto.production.RequestsProductionsDto;
import com.trade_accounting.services.interfaces.production.RequestsProductionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Requests Productions Rest Controller", description = "CRUD  операции с заказами на производство")
@Api(tags = "Requests Productions Rest Controller")
@RequestMapping("/api/processingorder")
@RequiredArgsConstructor

public class RequestsProductionsRestController {

    private final RequestsProductionsService requestsProductionsService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех заказов на производство")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка заказов на производство"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RequestsProductionsDto>> getAll() {
        return ResponseEntity.ok(requestsProductionsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение заказа на производство по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение заказа на производство по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RequestsProductionsDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти заказ на производство")
                                                    @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(requestsProductionsService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового заказа на производство")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Заказ на производство создан"),
            @ApiResponse(code = 201, message = "Запрос принят и заказ на производство добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RequestsProductionsDto> create(@ApiParam(name = "requestsProductionsDto",
            value = "DTO заказа на производство, которое необходимо создать")
                                                   @RequestBody RequestsProductionsDto requestsProductionsDto) {
        return ResponseEntity.ok(requestsProductionsService.create(requestsProductionsDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о заказе на производство")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о заказе на производство обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о заказе на производство обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RequestsProductionsDto> update(@ApiParam(name = "requestsProductionsDto",
            value = "DTO заказа на производство, которую необходимо обновить")
                                                   @RequestBody RequestsProductionsDto requestsProductionsDto) {
        return ResponseEntity.ok(requestsProductionsService.update(requestsProductionsDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление заказа на производство по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Заказ на производство удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RequestsProductionsDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить заказ на производство")
                                                       @PathVariable("id") Long id) {
        requestsProductionsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
