package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.dto.finance.AgentReportsDto;
import com.trade_accounting.services.interfaces.finance.AgentReportsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AgentReports Rest Controller", description = "CRUD операции с отчётами комиссионера")
@Api(tags = "AgentReports Rest Controller")
@RequestMapping("/api/agentReports")
public class AgentReportsRestController {

    private final AgentReportsService agentReportsService;

    public AgentReportsRestController(AgentReportsService agentReportsService) {
        this.agentReportsService = agentReportsService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех отчётов комиссионера")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех отчётов комиссионера"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<AgentReportsDto>> getAll() {
        List<AgentReportsDto> res = agentReportsService.getAll();
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный отчёт комиссионера по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отчёт комиссионера найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<AgentReportsDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти отчёт комиссионера") @PathVariable(name = "id") Long id) {
        AgentReportsDto agentReportsDto = agentReportsService.getById(id);
        return ResponseEntity.ok(agentReportsDto);
    }

    @ApiOperation(value = "create", notes = "Создает отчёт комиссионера на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отчёт комиссионера успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<AgentReportsDto> create(@ApiParam(name = "agentReportsDto", value = "DTO отчёта комиссионера, который необходимо создать")
                                                  @RequestBody AgentReportsDto agentReportsDto) {
        return ResponseEntity.ok().body(agentReportsService.create(agentReportsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет отчёт комиссионера на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отчёт комиссионера успешно обновлён"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<AgentReportsDto> update(@ApiParam(name = "agentReportsDto", value = "DTO отчёта комиссионера, который необходимо создать")
                                                  @RequestBody AgentReportsDto agentReportsDto) {
        return ResponseEntity.ok().body(agentReportsService.update(agentReportsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет отчёт комиссионера на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Отчёт комиссионера успешно удалён"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<AgentReportsDto> deleteById(@ApiParam(name = "id",
            value = "ID отчёта комиссионера, который необходимо удалить")
                                                      @PathVariable(name = "id") Long id) {
        agentReportsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
