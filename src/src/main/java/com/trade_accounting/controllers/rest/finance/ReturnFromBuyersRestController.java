package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.ReturnFromBuyers;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import com.trade_accounting.services.interfaces.finance.ReturnFromBuyersService;
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
@Tag(name = "ReturnFromBuyers Rest Controller", description = "CRUD операции с возратами от покупателей")
@Api(tags = "ReturnFromBuyers Rest Controller")
@RequestMapping("api/returnFromBuyers")
@RequiredArgsConstructor
public class ReturnFromBuyersRestController {
    private final ReturnFromBuyersService returnFromBuyersService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов от покуателей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение возвратов от покупателей"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ReturnFromBuyersDto>> getAll() {
        return ResponseEntity.ok(returnFromBuyersService.getAll());
    }

    @GetMapping("/searchReturnFromBuyers")
    @ApiOperation(value = "searchReturnFromBuyers", notes = "Получение списка возвратов от покупателей по заданным параметрам")
    public ResponseEntity<List<ReturnFromBuyersDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse", params = "warehouse", spec = LikeIgnoreCase.class),
                    @Spec(path = "contractor", params = "contractor", spec = LikeIgnoreCase.class),
                    @Spec(path = "company", params = "company", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class),
            }) Specification<ReturnFromBuyers> spec) {
        return ResponseEntity.ok(returnFromBuyersService.search(spec));
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "getById", notes = "Получение возврата от покупателя по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат от покупателя найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnFromBuyersDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти возврат от поупателя")
                                                       @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(returnFromBuyersService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового возврата от покупателя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат от покупателя создан"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат от покупателя добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnFromBuyersDto> create(@ApiParam(name = "returnFromBuyersDto",
            value = "DTO возврата от покупателей, которую необходимо создать")
                                                      @RequestBody ReturnFromBuyersDto returnFromBuyersDto) {
        return ResponseEntity.ok().body(returnFromBuyersService.create(returnFromBuyersDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о возврате от покупателя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате от покупателя обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о возврате от покупателя обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnFromBuyersDto> update(@ApiParam(name = "returnFromBuyersDto", value = "DTO возврат от покупателя, которую необходимо обновить")
                                                      @RequestBody ReturnFromBuyersDto returnFromBuyersDto) {
        return ResponseEntity.ok().body(returnFromBuyersService.update(returnFromBuyersDto));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ApiOperation(value = "deleteById", notes = "Удаление возврата от покупателя по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат от покупателя удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnFromBuyersDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить возврат от покупателя")
                                                          @PathVariable(name = "id") Long id) {
        returnFromBuyersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
