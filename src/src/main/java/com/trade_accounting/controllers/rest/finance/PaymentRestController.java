package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.Payment;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.repositories.finance.PaymentRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Payment Rest Controller", description = "CRUD  операции с платежами")
@Api(tags = "Payment Rest Controller")
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;
    private final CheckEntityService checkEntityService;
    private final PaymentRepository paymentRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех платежей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка платежей"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PaymentDto>> getAll() {
        List<PaymentDto> paymentDtoList = paymentService.getAll();
        return ResponseEntity.ok(paymentDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение платежа по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PaymentDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти платеж")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) paymentRepository, id);
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового платежа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж создан"),
            @ApiResponse(code = 201, message = "Запрос принят и платеж добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "paymentDto", value = "DTO платежа, который необходимо создать")
                                    @RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok().body(paymentService.create(paymentDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о платеже")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о платеже обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о платеже обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "paymentDto", value = "DTO платежа, который необходимо обновить")
                                    @RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok().body(paymentService.update(paymentDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление платежа по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить платеж")
                                        @PathVariable(name = "id") Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    @ApiOperation(value = "filter", notes = "Получение списка платежей по заданным параметрам")
    public ResponseEntity<List<PaymentDto>> filterAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "time", params = "time", spec = Equal.class),
                    @Spec(path = "typeOfPayment", params = "typeOfPayment", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "contract.number", params = "contractDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "project.name", params = "projectDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "sum", params = "sum", spec = Equal.class),
                    @Spec(path = "number", params = "number", spec = Like.class),
                    @Spec(path = "typeOfDocument", params = "typeOfDocument", spec = Equal.class),
                    @Spec(path = "paymentMethods", params = "paymentMethods", spec = Equal.class),
                    @Spec(path = "expenseItem", params = "expenseItem", spec = Equal.class)
            }) Specification<Payment> spec) {
        return ResponseEntity.ok(paymentService.filter(spec));
    }

    @GetMapping("/search/{search}")
    @ApiOperation(value = "search", notes = "Поиск по платежам")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный поиск по платежам"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PaymentDto>> search(@ApiParam(name = "search", type = "String",
            value = "Переданная строка, по которой необходимо найти платежи")
                                                   @PathVariable(name = "search") String search) {
        List<PaymentDto> paymentDtoList = paymentService.search(search);
        return ResponseEntity.ok(paymentDtoList);
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину платежа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж перенесен в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить платеж")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) paymentRepository, id);
        paymentService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление платежа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж восстановлен"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить платеж")
                                                               @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) paymentRepository, id);
        paymentService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }

}
