package src.main.java.com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.retail.RetailShift;
import com.trade_accounting.repositories.retail.RetailShiftRepository;
import com.trade_accounting.services.interfaces.retail.RetailShiftService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
@Tag(name = "Internal Order Rest Controller", description = "CRUD  операции со внутренними заказами")
@Api(tags = "Internal Order Rest Controller")
@RequestMapping("/api/retailshift")
@RequiredArgsConstructor
public class RetailShiftRestController {

    private final CheckEntityService checkEntityService;
    private final RetailShiftService retailShiftService;
    private final RetailShiftRepository retailShiftRepository;


    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех внутренних заказов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка внутренних заказов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailShiftDto>> getAll() {
        return ResponseEntity.ok(retailShiftService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение свнутреннего заказа по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailShiftDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти внутренний заказ")
                                                  @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailShiftRepository, id);

        return ResponseEntity.ok(retailShiftService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового внутреннего заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внутренний заказ создан"),
            @ApiResponse(code = 201, message = "Запрос принят и внутренний заказ добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailShiftDto> create(@ApiParam(name = "internalOrderDto",
            value = "DTO внутреннего заказа, которое необходимо создать")
                                                 @RequestBody RetailShiftDto retailShiftDto) {
        return ResponseEntity.ok(retailShiftService.create(retailShiftDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о внутреннем заказе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о внутреннем заказе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о внутреннем заказе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailShiftDto> update(@ApiParam(name = "internalOrderDto",
            value = "DTO внутреннего заказа, которую необходимо обновить")
                                                 @RequestBody RetailShiftDto retailShiftDto) {
        return ResponseEntity.ok(retailShiftService.update(retailShiftDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление внутреннего заказа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внутренний заказ удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailShiftDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить внутренний заказ")
                                                     @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailShiftRepository, id);
        retailShiftService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "search", notes = "Получение списка смен по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка смен по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailShiftDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(retailShiftService.search(value));
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка смен по фильтру")
    public ResponseEntity<List<RetailShiftDto>> searchByFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "dataOpen", params = "dataOpen", spec = Equal.class),
                    @Spec(path = "dataClose", params = "dataClose", spec = Equal.class),
                    @Spec(path = "retailStore.name", params = "retailStoreDto", spec = Equal.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Equal.class),
                    @Spec(path = "bank", params = "bank", spec = Equal.class),
                    @Spec(path = "revenuePerShift", params = "revenuePerShift", spec = Equal.class),
                    @Spec(path = "received", params = "received", spec = Equal.class),
                    @Spec(path = "amountOfDiscounts", params = "amountOfDiscounts", spec = Equal.class),
                    @Spec(path = "commission_amount", params = "commission_amount", spec = Equal.class),
                    @Spec(path = "sent", params = "sent", spec = Equal.class),
                    @Spec(path = "printed", params = "printed", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<RetailShift> spec) {
        return ResponseEntity.ok(retailShiftService.search(spec));
    }

}
