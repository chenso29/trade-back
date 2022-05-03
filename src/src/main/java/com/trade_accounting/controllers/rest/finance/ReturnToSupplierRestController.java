package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.repositories.finance.ReturnToSupplierRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.ReturnToSupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
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
@Tag(name = "ReturnToSupplier Rest Controller", description = "CRUD операции с возратами поставщикам")
@Api(tags = "ReturnToSupplier Rest Controller")
@RequestMapping("api/returnToSupplier")
@RequiredArgsConstructor
public class ReturnToSupplierRestController {

    private final ReturnToSupplierService returnToSupplierService;
    private final CheckEntityService checkEntityService;
    private final ReturnToSupplierRepository returnToSupplierRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов поставщикам")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение возвратов поставщикам"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ReturnToSupplierDto>> getAll() {
        return ResponseEntity.ok(returnToSupplierService.getAll());
    }

    @GetMapping("/search/{nameFilter}")
    @ApiOperation(value = "searchByNameFilter", notes = "Получение списка всех возвратов поставщикам")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов поставщикам"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ReturnToSupplierDto>> searchByNameFilter(@ApiParam(name = "nameFilter",
            value = "Переданный в URL nameFilter, по которому необходимо найти возврат поставщика") @PathVariable String nameFilter) {
        return ResponseEntity.ok(returnToSupplierService.searchByString(nameFilter));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение возврата поставщику по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат поставщику найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnToSupplierDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат поставщику")
                                                       @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) returnToSupplierRepository, id);
        return ResponseEntity.ok(returnToSupplierService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового возврата поставщику")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат поставщику создан"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат поставщику добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ReturnToSupplierDto> create(@ApiParam(name = "modelDto",
            value = "DTO возврата поставщику, который необходимо создать") @RequestBody ReturnToSupplierDto modelDto) {
        return ResponseEntity.ok(returnToSupplierService.create(modelDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление возврата поставщика")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате поставщику"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат поставщику обновлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ReturnToSupplierDto> update(@ApiParam(name = "modelDto",
            value = "DTO возврата поставщику, который нужно обновить") @RequestBody ReturnToSupplierDto modelDto) {
        return ResponseEntity.ok(returnToSupplierService.update(modelDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаляет возврат поставщику на основе переданного ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат поставщику успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ReturnToSupplierDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить возврат поставщику") @PathVariable Long id) {
        returnToSupplierService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/queryReturnToSupplier")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка возвратов поставщику по заданным параметрам")
    public ResponseEntity<List<ReturnToSupplierDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "company.name", params = "companyDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "isSend", params = "send", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class)
            }) Specification<ReturnToSupplier> supplier) {
        return ResponseEntity.ok(returnToSupplierService.search(supplier));
    }

}
