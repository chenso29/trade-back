package src.main.java.com.trade_accounting.controllers.rest.finance;


import com.trade_accounting.models.entity.finance.Payout;
import com.trade_accounting.models.dto.finance.PayoutDto;
import com.trade_accounting.repositories.finance.PayoutRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.PayoutService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Payout Rest Controller", description = "CRUD операции с выплатами")
@Api(tags = "Payout Rest Controller")
@RequestMapping("/api/payout")
@RequiredArgsConstructor
public class PayoutRestController {

    private final PayoutService payoutService;
    private final CheckEntityService checkEntityService;
    private final PayoutRepository payoutRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех выплат")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка выплат"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PayoutDto>> getAll() {
        return ResponseEntity.ok(payoutService.getAll());
    }

    @GetMapping("/search/{searchTerm}")
    @ApiOperation(value = "searchTerm", notes = "Получение списка некоторых выплат")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение отф. списка выплат"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PayoutDto>> getAllByParameters(@ApiParam(name = "searchTerm",
            value = "Переданный в URL searchTerm, по которому необходимо найти выплаты")
                                                      @PathVariable(name = "searchTerm") String searchTerm) {
        List<PayoutDto> payoutDtoList = payoutService.getAllByParametrs(searchTerm);
        return ResponseEntity.ok(payoutDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение данных о выплате")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение данных о выплате"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PayoutDto> getById(@ApiParam(name = "id" , type = "Long",
            value = "Переданный в URL id, по которому необходимо найти информацию о выплате")
                                             @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) payoutRepository, id);

        return ResponseEntity.ok(payoutService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление выплаты")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Выплата создана"),
            @ApiResponse(code = 201, message = "Запрос принят и выплата добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PayoutDto> create(@ApiParam(name = "payoutDto",
            value = "DTO выплаты, которую необходимо создать")
                                                @RequestBody PayoutDto payoutDto) {
        return ResponseEntity.ok(payoutService.create(payoutDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о выплате")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о выплате обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о выплате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PayoutDto> update(@ApiParam(name = "payoutDto",
            value = "DTO выплаты, которую необходимо обновить")
                                                @RequestBody PayoutDto payoutDto) {
        return ResponseEntity.ok(payoutService.update(payoutDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление выплаты по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Выплата удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PayoutDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить выплату")
                                                    @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) payoutRepository, id);
        payoutService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка Выплат по фильтру")
    public ResponseEntity<List<PayoutDto>> searchByFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "retailStore.name", params = "retailDto", spec = Equal.class),
                    @Spec(path = "whoWasPaid", params = "whoWasPaid", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Equal.class),
                    @Spec(path = "isSent", params = "send", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
                    @Spec(path = "retailStore.revenue", params = "retailSumDto", spec = Equal.class),
            })Specification<Payout> spec){
        return  ResponseEntity.ok(payoutService.search(spec));
    }

}
