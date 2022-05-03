package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.services.interfaces.company.BankAccountService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Bank Account Rest Controller", description = "CRUD операции с банковскими аккаунтами")
@Api(tags = "Bank Account Rest Controller")
@RequestMapping("/api/bank/account")
@RequiredArgsConstructor
public class BankAccountRestController {

    private final BankAccountService bankAccountService;
    private final CheckEntityService checkEntityService;
    private final BankAccountRepository bankAccountRepository;

    @ApiOperation(value = "getBankByBic", notes = "Возвращает определенный банк по bic")
    @GetMapping("/bic/{uniqBic}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Банковский аккаунт найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BankAccountDto> getBankByBic(@ApiParam(name = "uniqBic", value = "uniqBic переданный в URL по которому необходимо найти банковский аккаунт")
                                                       @PathVariable(name = "uniqBic") String uniqBic) {
        BankAccountDto bankAccount = bankAccountService.getBankByBic(uniqBic);
        return ResponseEntity.ok(bankAccount);
    }

    @ApiOperation(value = "getAll", notes = "Возвращает лист всех банковских аккаунтов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа банковских аккаунтов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BankAccountDto>> getAll() {
        List<BankAccountDto> accounts = bankAccountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @ApiOperation(value = "getListBankUniqueBic", notes = "Возвращает лист всех банковских аккаунтов")
    @GetMapping("/bic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа банковских аккаунтов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<String>> getListBankUniqueBic() {
        List<String> accounts = bankAccountService.getBankUniqueBic();
        return ResponseEntity.ok(accounts);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный по айди банковский аккаунт")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Банковский аккаунт найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BankAccountDto> getById(@ApiParam(name = "id", value = "ID переданный в URL по которому необходимо найти банковский аккаунт")
                                                  @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) bankAccountRepository, id);
        return ResponseEntity.ok(bankAccountService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает банковский аккаунт на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "bankAccountDto", value = "DTO банковского аккаунта, который необходимо создать") @RequestBody BankAccountDto bankAccountDto) {
        return ResponseEntity.ok().body(bankAccountService.create(bankAccountDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет банковский аккаунт на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "bankAccountDto", value = "DTO банковского аккаунта, c обновленными данными") @RequestBody BankAccountDto bankAccountDto) {
        return ResponseEntity.ok().body(bankAccountService.update(bankAccountDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет банковский аккаунт на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", value = "ID банковского аккаунта, который необходимо удалить") @PathVariable(name = "id") Long id) {
        bankAccountService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
