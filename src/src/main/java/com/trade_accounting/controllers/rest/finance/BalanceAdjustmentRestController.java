package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.BalanceAdjustment;
import com.trade_accounting.models.dto.finance.BalanceAdjustmentDto;
import com.trade_accounting.repositories.finance.BalanceAdjustmentRepository;
import com.trade_accounting.services.interfaces.finance.BalanceAdjustmentService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "BalanceAdjustment Rest Controller", description = "CRUD операции с корректировками баланса")
@Api(tags = "BalanceAdjustment Rest Controller")
@RequestMapping("api/balanceAdjustment")
@RequiredArgsConstructor
public class BalanceAdjustmentRestController {

    private final BalanceAdjustmentService balanceAdjustmentService;
    private final CheckEntityService checkEntityService;
    private final BalanceAdjustmentRepository balanceAdjustmentRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех корректировок баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение корректировок баланса"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BalanceAdjustmentDto>> getAll() {
        return ResponseEntity.ok(balanceAdjustmentService.getAll());
    }

    @GetMapping("/search/{nameFilter}")
    @ApiOperation(value = "searchByNameFilter", notes = "Получение списка всех корректировок баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка корректировок баланса"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BalanceAdjustmentDto>> searchByNameFilter(@ApiParam(name = "nameFilter",
            value = "Переданный в URL nameFilter, по которому необходимо найти корректировку баланса") @PathVariable String nameFilter) {
        return ResponseEntity.ok(balanceAdjustmentService.searchByString(nameFilter));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение корректировки баланса по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Корректировка баланса найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BalanceAdjustmentDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти корректировку баланса")
                                                       @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) balanceAdjustmentRepository, id);
        return ResponseEntity.ok(balanceAdjustmentService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой корректировки баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Корректировка баланса создана"),
            @ApiResponse(code = 201, message = "Запрос принят и корректировка баланса добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<BalanceAdjustmentDto> create(@ApiParam(name = "modelDto",
            value = "DTO корректировки баланса, который необходимо создать") @RequestBody BalanceAdjustmentDto modelDto) {
        return ResponseEntity.ok(balanceAdjustmentService.create(modelDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление корректировки баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о корректировки баланса"),
            @ApiResponse(code = 201, message = "Запрос принят и корректировка баланса обновлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<BalanceAdjustmentDto> update(@ApiParam(name = "modelDto",
            value = "DTO корректировки баланса, который нужно обновить") @RequestBody BalanceAdjustmentDto modelDto) {
        return ResponseEntity.ok(balanceAdjustmentService.update(modelDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаляет корректировку баланса на основе переданного ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Корректировки баланса успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<BalanceAdjustmentDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить корректировку баланса") @PathVariable Long id) {
        balanceAdjustmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/queryBalanceAdjustment")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка корректировок баланса по заданным параметрам")
    public ResponseEntity<List<BalanceAdjustmentDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "company.name", params = "companyDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "account", params = "account", spec = Equal.class),
                    @Spec(path = "cashOffice", params = "cashOffice", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
                    @Spec(path = "dateChanged", params = "dateChanged", spec = Equal.class),
                    @Spec(path = "whoChanged", params = "whoChanged", spec = Equal.class),
            }) Specification<BalanceAdjustment> supplier) {
        return ResponseEntity.ok(balanceAdjustmentService.search(supplier));
    }

}
