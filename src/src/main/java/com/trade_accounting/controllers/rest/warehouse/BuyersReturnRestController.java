package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
import com.trade_accounting.repositories.warehouse.BuyersReturnRepository;
import com.trade_accounting.services.interfaces.warehouse.BuyersReturnService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
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
@Tag(name = "BuyersReturn Rest Controller", description = "CRUD  операции с накладными")
@Api(tags = "BuyersReturn Rest Controller")
@RequestMapping("/api/buyersreturn")
@RequiredArgsConstructor
public class BuyersReturnRestController {

    private final BuyersReturnService buyersReturnService;
    private final CheckEntityService checkEntityService;
    private final BuyersReturnRepository buyersReturnRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BuyersReturnDto>> getAll() {
        List<BuyersReturnDto> buyersReturnDtoList;
        buyersReturnDtoList = buyersReturnService.getAll();
        return ResponseEntity.ok(buyersReturnDtoList);
    }

    @GetMapping("/getByContractorId{id}")
    @ApiOperation(value = "getByContractorId", notes = "Получение списка всех возвратов по контрагенту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BuyersReturnDto>> getByContractorId(@PathVariable Long id) {
        return ResponseEntity.ok(buyersReturnService.getByContractorId(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение возврата по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BuyersReturnDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) buyersReturnRepository, id);
        return ResponseEntity.ok(buyersReturnService.getById(id));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "searchByString", notes = "Search of invoices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка накладных"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BuyersReturnDto>> search(@RequestParam("search") String search) {
        return ResponseEntity.ok(buyersReturnService.findBySearch(search));
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "search", notes = "Получение списка счетов по заданным параметрам")
    public ResponseEntity<List<BuyersReturnDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "company.name", params = "company", spec = Like.class),
                    @Spec(path = "contractor.name", params = "contractor", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouse", spec = LikeIgnoreCase.class),
                    @Spec(path = "sum", params = "sum", spec = Equal.class),
                    @Spec(path = "isSent", params = "isSent", spec = Equal.class),
                    @Spec(path = "isPrint", params = "isPrint", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<BuyersReturn> spec) {
        return ResponseEntity.ok(buyersReturnService.search(spec));
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
    public ResponseEntity<BuyersReturnDto> create(@ApiParam(name = "buyersReturnDto", value = "DTO возврата, которую необходимо создать")
                                              @RequestBody BuyersReturnDto buyersReturnDto) {
        return ResponseEntity.ok().body(buyersReturnService.create(buyersReturnDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об отгрузке")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о возврате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "buyersReturnDto", value = "DTO возврата, которую необходимо обновить")
                                    @RequestBody BuyersReturnDto buyersReturnDto) {
        return ResponseEntity.ok().body(buyersReturnService.update(buyersReturnDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление возврата по ее id")
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
        buyersReturnService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
