package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Warehouse Rest Controller", description = "CRUD операции со складами")
@Api(tags = "Warehouse Account Rest Controller")
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseRestController {

    private final WarehouseService warehouseService;
    private final CheckEntityService checkEntityService;
    private final WarehouseRepository warehouseRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех складов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех складов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<WarehouseDto>> getAll() {
        List<WarehouseDto> warehouseDtos = warehouseService.getAll();
        return ResponseEntity.ok(warehouseDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный склад по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Склад найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<WarehouseDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) warehouseRepository, id);
        return ResponseEntity.ok(warehouseService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает склад на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Склад успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> create(@RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok().body(warehouseService.create(warehouseDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет склад на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Склад успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> update(@RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok().body(warehouseService.update(warehouseDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет склад на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Склад успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        warehouseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка работников по заданным параметрам")
    public ResponseEntity<List<WarehouseDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(warehouseService.searchByString(search));
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка складов по заданным параметрам")
    public ResponseEntity<List<WarehouseDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = LikeIgnoreCase.class),
                    @Spec(path = "address", params = "address", spec = LikeIgnoreCase.class),
                    @Spec(path = "commentToAddress", params = "commentToAddress", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class)
            }) Specification<Warehouse> specification) {

        return ResponseEntity.ok(warehouseService.search(specification));
    }
}
