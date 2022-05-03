package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.MutualSettlements;
import com.trade_accounting.models.dto.finance.MutualSettlementsDto;
import com.trade_accounting.services.interfaces.finance.MutualSettlementsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
@Tag(name = "MutualSettlements Rest Controller", description = "CRUD операции с взаимовычатами")
@Api(tags = "MutualSettlements Rest Controller")
@RequestMapping("api/mutualSettlements")
public class MutualSettlementsRestController {

    private final MutualSettlementsService mutualSettlementsService;

    public MutualSettlementsRestController(MutualSettlementsService mutualSettlementsService) {
        this.mutualSettlementsService = mutualSettlementsService;
    }

    @ApiOperation(value = "getAll", notes = "возвращает список всех взаиморасчётов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех взаиморасчётов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<MutualSettlementsDto>> getAll() {
        List<MutualSettlementsDto> mutualSettlements = mutualSettlementsService.getAll();
        return ResponseEntity.ok(mutualSettlements);
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка Взаиморасчетов по фильтру")
    public ResponseEntity<List<MutualSettlementsDto>> searchByFilter(
            @And({
                    @Spec(path = "contractor.id", params = "contractorId", spec = Equal.class),
                    @Spec(path = "employee.id", params = "employeeId", spec = Equal.class),
                    @Spec(path = "initialBalance", params = "initialBalance", spec = Equal.class),
                    @Spec(path = "income", params = "income", spec = Equal.class),
                    @Spec(path = "expenses", params = "expenses", spec = Equal.class),
                    @Spec(path = "finalBalance", params = "finalBalance", spec = Equal.class),
            })Specification<MutualSettlements> spec){
        return  ResponseEntity.ok(mutualSettlementsService.search(spec));
    }



    @ApiOperation(value = "getById", notes = "Возвращает определенный взаиморасчёт по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Взаиморасчёт найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<MutualSettlementsDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти взаиморасчёт") @PathVariable(name = "id") Long id) {
        MutualSettlementsDto mutualSettlementsDto = mutualSettlementsService.getById(id);
        return ResponseEntity.ok(mutualSettlementsDto);
    }

    @ApiOperation(value = "create", notes = "Создает взаиморасчёт на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Взаиморасчёт успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<MutualSettlementsDto> create(@ApiParam(name = "customerBalanceListDto", value = "DTO взаиморасчёта, которое необходимо создать")
                                                       @RequestBody MutualSettlementsDto mutualSettlementsDto) {
        return ResponseEntity.ok().body(mutualSettlementsService.create(mutualSettlementsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет взаиморасчёт на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Взаиморасчёт успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<MutualSettlementsDto> update(@ApiParam(name = "customersBalanceListDto", value = "DTO взаиморсачёта, с обновлёнными данными")
                                                       @RequestBody MutualSettlementsDto mutualSettlementsDto) {
        return ResponseEntity.ok().body(mutualSettlementsService.update(mutualSettlementsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет взаиморсчёт на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Взаиморасчёт успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<MutualSettlementsDto> geleteById(@ApiParam(name = "id",
            value = "ID взаиморасчёта, который необходимо удалить")
                                                           @PathVariable(name = "id") Long id) {
        mutualSettlementsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}