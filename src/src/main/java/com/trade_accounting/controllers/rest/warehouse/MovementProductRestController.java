package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.MovementProductDto;
import com.trade_accounting.repositories.warehouse.MovementProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.MovementProductService;
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
@Tag(name = "Movement Product Rest Controller", description = "CRUD  операции с товарами, которые перемещаем между складами")
@Api(tags = "Movement Product Rest Controller")
@RequestMapping("/api/movement/product")
@RequiredArgsConstructor
public class MovementProductRestController {

    private final MovementProductService movementProductService;
    private final CheckEntityService checkEntityService;
    private final MovementProductRepository movementProductRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех перемещенных товаров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение перемещенных товаров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<MovementProductDto>> getAll() {
        return ResponseEntity.ok(movementProductService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение перемещенного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение перемещенного товара по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти перемещенный товар")
                                                      @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) movementProductRepository, id);
        return ResponseEntity.ok(movementProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового перемещенного товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Перемещенный товар добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и перемещенный товар добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementProductDto> create(@ApiParam(name = "movementProductDto",
            value = "DTO перемещенного товара, который необходимо создать")
                                                     @RequestBody MovementProductDto movementProductDto) {
        return ResponseEntity.ok(movementProductService.create(movementProductDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об перемещенном товаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о перемещенном товаре добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о перемещенном товаре обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementProductDto> update(@ApiParam(name = "movementProductDto",
            value = "DTO перемещенного товара, который необходимо обновить")
                                                     @RequestBody MovementProductDto movementProductDto) {
        return ResponseEntity.ok(movementProductService.update(movementProductDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление перемещенного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Перемещенный товар удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementProductDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить перемещенный товар")
                                                         @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) movementProductRepository, id);
        movementProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
