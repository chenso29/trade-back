package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.services.interfaces.company.ContractorStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Status Rest Controller", description = "CRUD  операции со статусами")
@Api(tags = "Status Rest Controller")
@RequestMapping("/api/contractor_status")
public class ContractorStatusRestController {

    private final ContractorStatusService statusService;

    public ContractorStatusRestController(ContractorStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех статусов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка статусов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorStatusDto>> getAll() {
        List<ContractorStatusDto> statusDtoList = statusService.getAll();
        return ResponseEntity.ok(statusDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение статуса по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Статус найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorStatusDto> getById(@ApiParam(name = "id",
            value = "Переданный в URL id по которому необходимо найти статус")
                                                       @PathVariable(name = "id") Long id) {
        ContractorStatusDto statusDto = statusService.getById(id);
        return ResponseEntity.ok(statusDto);
    }
}
