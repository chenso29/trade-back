package src.main.java.com.trade_accounting.controllers.rest.production;

import com.trade_accounting.models.dto.production.ProductionDto;
import com.trade_accounting.services.interfaces.production.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Production Rest Controller", description = "CRUD операции с производством")
@Api(tags = "Production Rest Controller")
@RequestMapping("/api/production")
public class ProductionRestController {

    private final ProductionService productionService;

    public ProductionRestController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех производств")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех производств"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductionDto>> getAll() {
        List<ProductionDto> productionDtoList = productionService.getAll();
        return ResponseEntity.ok(productionDtoList);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенное производство по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производство найдено"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти производство") @PathVariable(name = "id") Long id) {
        ProductionDto productionDto = productionService.getById(id);
        return ResponseEntity.ok(productionDto);
    }

    @ApiOperation(value = "create", notes = "Создает производство на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производство успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionDto> create(@ApiParam(name = "productionDto", value = "DTO производства, которое необходимо создать")
                                                @RequestBody ProductionDto productionDto) {
        return ResponseEntity.ok().body(productionService.create(productionDto));
    }


    @ApiOperation(value = "update", notes = "Обновляет производство на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производство успешно обновлено"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionDto> update(@ApiParam(name = "productionDto", value = "DTO производства, c обновленными данными")
                                                @RequestBody ProductionDto productionDto) {
        return ResponseEntity.ok().body(productionService.update(productionDto));
    }


    @ApiOperation(value = "deleteById", notes = "Удаляет производство на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производство успешно удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionDto> deleteById(@ApiParam(name = "id",
            value = "ID производства, который необходимо удалить")
                                                    @PathVariable(name = "id") Long id) {
        productionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
