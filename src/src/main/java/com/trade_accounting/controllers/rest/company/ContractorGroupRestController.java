package src.main.java.com.trade_accounting.controllers.rest.company;


import com.trade_accounting.models.dto.company.ContractorGroupDto;
import com.trade_accounting.repositories.company.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.ContractorGroupService;
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
@Tag(name = "Contractor Group Rest Controller", description = "CRUD  операции с группой контрагентов")
@Api(tags = "Contractor Group Rest Controller")
@RequestMapping("/api/contractor/group")
@RequiredArgsConstructor
public class ContractorGroupRestController {
    private final ContractorGroupService contractorGroupService;
    private final CheckEntityService checkEntityService;
    private final ContractorGroupRepository contractorGroupRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех групп")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка групп"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorGroupDto>> getAll() {
        List<ContractorGroupDto> contractorGroupDtos = contractorGroupService.getAll();
        return ResponseEntity.ok(contractorGroupDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение группы контрагентов по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorGroupDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти группу")
                                                      @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) contractorGroupRepository, id);
        return ResponseEntity.ok(contractorGroupService.getById(id));

    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение новой группы контрагентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа создана"),
            @ApiResponse(code = 201, message = "Запрос принят и группа добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "ContractorGroupDto", value = "DTO группы, которую необходимо создать")
                                    @RequestBody ContractorGroupDto contractorGroupDto) {
        return ResponseEntity.ok().body(contractorGroupService.create(contractorGroupDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о группе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о группе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о группе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "ContractorGroupDto", value = "DTO группы, которую необходимо обновить")
                                    @RequestBody ContractorGroupDto contractorGroupDto) {
        return ResponseEntity.ok().body(contractorGroupService.update(contractorGroupDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить групу")
                                        @PathVariable(name = "id") Long id) {
        contractorGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
