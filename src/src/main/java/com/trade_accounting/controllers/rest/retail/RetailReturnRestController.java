package src.main.java.com.trade_accounting.controllers.rest.retail;


import com.trade_accounting.models.entity.retail.RetailReturn;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import com.trade_accounting.repositories.retail.RetailReturnRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.retail.RetailReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
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
@Tag(name = "RetailReturns Rest Controller", description = "CRUD  операции с возвратами")
@Api(tags = "RetailReturns Rest Controller")
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class RetailReturnRestController {

    private final CheckEntityService checkEntityService;
    private final RetailReturnService retailReturnService;
    private final RetailReturnRepository retailReturnRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailReturnDto>> getAll() {
        List<RetailReturnDto> retailReturnDtoList;
        retailReturnDtoList = retailReturnService.getAll();
        return ResponseEntity.ok(retailReturnDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение возврата по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailReturnDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailReturnRepository, id);
        return ResponseEntity.ok(retailReturnService.getById(id));
    }

    @ApiOperation(value = "search", notes = "Получение списка возвратов по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailReturnDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(retailReturnService.search(value));
    }

    @GetMapping("/searchRetailReturns")
    @ApiOperation(value = "searchRetailReturns", notes = "Получение списка возвратов по заданным параметрам")
    public ResponseEntity<List<RetailReturnDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "retailStore.id", params = "retailStoreId", spec = Like.class),

                    @Spec(path = "cashAmount", params = "cashAmount", spec = Equal.class),
                    @Spec(path = "cashlessAmount", params = "cashlessAmount", spec = Equal.class),
                    @Spec(path = "totalAmount", params = "totalAmount", spec = Equal.class),
                    @Spec(path = "isSpend", params = "spend", spec = Equal.class),
                    @Spec(path = "isSend", params = "send", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<RetailReturn> spec) {
        return ResponseEntity.ok(retailReturnService.search(spec));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового возврата")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат создан"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailReturnDto> create(@ApiParam(name = "retailReturnDto", value = "DTO возврата, которого необходимо создать")
                                              @RequestBody RetailReturnDto retailReturnDto) {
        return ResponseEntity.ok().body(retailReturnService.create(retailReturnDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о возврате")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о возврате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "retailReturnDto", value = "DTO возврата, которого необходимо обновить")
                                    @RequestBody RetailReturnDto retailReturnDto) {
        return ResponseEntity.ok().body(retailReturnService.update(retailReturnDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление возврата по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить возврат")
                                        @PathVariable(name = "id") Long id) {
        retailReturnService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
