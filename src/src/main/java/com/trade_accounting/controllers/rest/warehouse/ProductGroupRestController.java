package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.repositories.warehouse.ProductGroupRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.ProductGroupService;
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
@Tag(name = "Product Group Rest Controller", description = "CRUD операции с товарными группами")
@Api(tags = "Product Group Rest Controller")
@RequestMapping("/api/productgroup")
@RequiredArgsConstructor
public class ProductGroupRestController {

    private final ProductGroupService productGroupService;
    private final CheckEntityService checkEntityService;
    private final ProductGroupRepository productGroupRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех товарных групп")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка товарных групп"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<ProductGroupDto>> getAll() {
        List<ProductGroupDto> productGroups = productGroupService.getAll();
        return ResponseEntity.ok(productGroups);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение товарной группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товарная группа успешно найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ProductGroupDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти товарную группу")
                                                   @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) productGroupRepository, id);
        return ResponseEntity.ok(productGroupService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Создание новой товарной группы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товарная группа записана"),
            @ApiResponse(code = 201, message = "Запрос принят и товарная группа добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "productGroupDto",
            value = "DTO товарной группы, которую необходимо создать")
                                    @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok().body(productGroupService.create(productGroupDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о товарной группе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о товарной группе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о товарной группе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "ProductGroupDto", value = "DTO товарной группы, которую необходимо обновить")
                                    @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok().body(productGroupService.update(productGroupDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление товарной группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Запись о товарной группе удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить товарную группу")
                                        @PathVariable(name = "id") Long id) {
        productGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
