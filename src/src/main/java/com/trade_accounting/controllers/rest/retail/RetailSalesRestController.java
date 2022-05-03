package src.main.java.com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.repositories.retail.RetailSalesRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.retail.RetailSalesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.StartingWith;
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
@Tag(name = "RetailSales Rest Controller", description = "CRUD операции с розничными продажами")
@Api(tags = "RetailSales Rest Controller")
@RequestMapping("/api/retail_sales")
@RequiredArgsConstructor
public class RetailSalesRestController {

    private final RetailSalesService retailSalesService;
    private final RetailSalesRepository retailSalesRepository;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список розничных продаж")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех розничных продаж"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailSalesDto>> getAll() {
        List<RetailSalesDto> retailSalesDtos = retailSalesService.getAll();
        return ResponseEntity.ok(retailSalesDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную розничную продажу по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Розничная продажа найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailSalesDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailSalesRepository,id);
        return ResponseEntity.ok(retailSalesService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает розничную продажу на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Розничная продажа успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailSalesDto> create(@RequestBody RetailSalesDto retailSalesDto) {
        return ResponseEntity.ok().body(retailSalesService.create(retailSalesDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет розничную продажу на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Розничная продажа успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailSalesDto> update(@RequestBody RetailSalesDto retailSalesDto) {
        checkEntityService.checkExists((JpaRepository) retailSalesRepository,retailSalesDto.getId());
        return ResponseEntity.ok().body(retailSalesService.update(retailSalesDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет розничную продажу на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Розничная продажа успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailSalesDto> deleteById(@PathVariable("id") Long id) {
        retailSalesService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "search", notes = "Получение списка розничных продаж по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка розничных продаж по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailSalesDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(retailSalesService.search(value));
    }

    @GetMapping("searchRetailSales")
    @ApiOperation(value = "searchRetailSales", notes = "Получение списка розничных продаж по заданным параметрам")
    public ResponseEntity<List<RetailSalesDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "retailStore.id", params = "retailStoreId", spec = Equal.class),
                    @Spec(path = "contractor.id", params = "contractorId", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyName", spec = StartingWith.class),
                    @Spec(path = "sumCash", params = "sumCash", spec = Equal.class),
                    @Spec(path = "sumNonСash", params = "sumNonСash", spec = Equal.class),
                    @Spec(path = "prepayment", params = "prepayment", spec = Equal.class),
                    @Spec(path = "sumDiscount", params = "sumDiscount", spec = Equal.class),
                    @Spec(path = "sum", params = "sum", spec = Equal.class),
                    @Spec(path = "sent", params = "sent", spec = Equal.class),
                    @Spec(path = "printed", params = "printed", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),

            }) Specification<RetailSales> spec) {
        return ResponseEntity.ok(retailSalesService.search(spec));
    }
}
