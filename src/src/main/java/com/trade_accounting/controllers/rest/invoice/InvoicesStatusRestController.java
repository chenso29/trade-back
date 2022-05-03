package src.main.java.com.trade_accounting.controllers.rest.invoice;


import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
import com.trade_accounting.repositories.invoice.InvoicesStatusRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.invoice.InvoicesStatusService;
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
@Tag(name = "Role Rest Controller", description = "CRUD операции с статусами накладной")
@Api(tags = "Role Rest Controller")
@RequestMapping("api/invoicestatus")
@RequiredArgsConstructor
public class InvoicesStatusRestController {

    private final InvoicesStatusService invoicesStatusService;
    private final CheckEntityService checkEntityService;
    private final InvoicesStatusRepository invoicesStatusRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех статусов накладной")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех статусов накладной"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoicesStatusDto>> getAll() {
        List<InvoicesStatusDto> invoicesStatusDto = invoicesStatusService.getAll();
        return ResponseEntity.ok(invoicesStatusDto);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный статус накладной по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Статус  накладной найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoicesStatusDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти статус  накладной",
            example = "1",
            required = true) @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) invoicesStatusRepository, id);
        return ResponseEntity.ok(invoicesStatusService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает статус накладной на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Статус накладной успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "invoicesStatusDto",
            value = "DTO роли, которую необходимо создать") @RequestBody InvoicesStatusDto invoicesStatusDto) {
        return ResponseEntity.ok().body(invoicesStatusService.create(invoicesStatusDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет cтатус на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Статус накладной успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "invoicesStatusDto",
            value = "DTO статуса накладной, который необходимо обновить")
                                    @RequestBody InvoicesStatusDto invoicesStatusDto) {
        return ResponseEntity.ok().body(invoicesStatusService.update(invoicesStatusDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет статус накладной на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Статус накладной успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<InvoicesStatusDto> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить статус накладной",
            example = "1",
            required = true
    ) @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) invoicesStatusRepository, id);
        invoicesStatusService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
