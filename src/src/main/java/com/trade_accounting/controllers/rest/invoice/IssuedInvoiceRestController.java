package src.main.java.com.trade_accounting.controllers.rest.invoice;

import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import com.trade_accounting.repositories.invoice.IssuedInvoiceRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.invoice.IssuedInvoiceService;
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
@Tag(name = "IssuedInvoice Rest Controller", description = "CRUD  операции с выданными накладными")
@Api(tags = "IssuedInvoice Rest Controller")
@RequestMapping("/api/issuedInvoice")
@RequiredArgsConstructor
public class IssuedInvoiceRestController {
    private final IssuedInvoiceService issuedInvoiceService;
    private final CheckEntityService checkEntityService;
    private final IssuedInvoiceRepository issuedInvoiceRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех накладных")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка накладных"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<IssuedInvoiceDto>> getAll() {
        List<IssuedInvoiceDto> issuedInvoiceDtos;
        issuedInvoiceDtos = issuedInvoiceService.getAll();
        return ResponseEntity.ok(issuedInvoiceDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение накладной по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<IssuedInvoiceDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти накладную")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) issuedInvoiceRepository, id);
        return ResponseEntity.ok(issuedInvoiceService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой накладной")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная создана"),
            @ApiResponse(code = 201, message = "Запрос принят и накладная добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<IssuedInvoiceDto> create(@ApiParam(name = "invoiceDto", value = "DTO накладной, которую необходимо создать")
                                             @RequestBody IssuedInvoiceDto issuedInvoiceDto) {
        return ResponseEntity.ok().body(issuedInvoiceService.create(issuedInvoiceDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о накладной")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о накладной обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о накладной обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "issuedInvoiceDto", value = "DTO накладной, которую необходимо обновить")
                                    @RequestBody IssuedInvoiceDto issuedInvoiceDto) {
        return ResponseEntity.ok().body(issuedInvoiceService.update(issuedInvoiceDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление накладной по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить накладную")
                                        @PathVariable(name = "id") Long id) {
        issuedInvoiceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
