package src.main.java.com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.dto.retail.RetailStoreDto;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.services.interfaces.retail.RetailStoreService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
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
@Tag(name = "RetailStore Rest Controller", description = "CRUD операции с точками продаж")
@Api(tags = "RetailStore Rest Controller")
@RequestMapping("/api/retail_stores")
@RequiredArgsConstructor
public class RetailStoreRestController {

    private final RetailStoreService retailStoreService;
    private final CheckEntityService checkEntityService;
    private final RetailStoreRepository retailStoreRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех точек продаж")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех точек продаж"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailStoreDto>> getAll() {
        List<RetailStoreDto> retailStoreDtos = retailStoreService.getAll();
        return ResponseEntity.ok(retailStoreDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную точку продаж по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Точка продаж найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailStoreDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailStoreRepository, id);
        return ResponseEntity.ok(retailStoreService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает точку продаж на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Точка продаж успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailStoreDto> create(@RequestBody RetailStoreDto retailStoreDto) {
        return ResponseEntity.ok().body(retailStoreService.create(retailStoreDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет точку продаж на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Точка продаж успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailStoreDto> update(@RequestBody RetailStoreDto retailStoreDto) {
        checkEntityService.checkExists((JpaRepository) retailStoreRepository, retailStoreDto.getId());
        return ResponseEntity.ok().body(retailStoreService.update(retailStoreDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет точку продаж на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Точка продаж успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailStoreDto> deleteById(@PathVariable("id") Long id) {
        retailStoreService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchRetailStoreByFilter")
    @ApiOperation(value = "searchRetailStoreByFilter", notes = "Получение списка точек продаж по заданным параметрам")
    public ResponseEntity<List<RetailStoreDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = Equal.class),
                    @Spec(path = "isActive", params = "isActive", spec = Like.class),
                    @Spec(path = "activityStatus", params = "activityStatus", spec = Equal.class),
                    @Spec(path = "revenue", params = "revenue", spec = Equal.class),
                    @Spec(path = "company.id", params = "companyId", spec = Like.class),
                    @Spec(path = "salesInvoicePrefix", params = "salesInvoicePrefix", spec = Equal.class),
                    @Spec(path = "defaultTaxationSystem", params = "defaultTaxationSystem", spec = Equal.class),
                    @Spec(path = "orderTaxationSystem", params = "orderTaxationSystem", spec = Equal.class),
                    @Spec(path = "cashiersIds", params = "cashiersIds", spec = Equal.class),
            }) Specification<RetailStore> spec) {
        return ResponseEntity.ok(retailStoreService.search(spec));
    }

    @ApiOperation(value = "search", notes = "Получение списка точек продаж по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка точек продаж по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailStoreDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(retailStoreService.search(value));
    }
}
