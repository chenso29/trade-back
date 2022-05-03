package src.main.java.com.trade_accounting.controllers.rest.production;

import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
import com.trade_accounting.repositories.production.TechnicalCardGroupRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.production.TechnicalCardGroupService;
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
@Tag(name = "Technical card group Rest Controller", description = "CRUD операции с группами технических карт")
@Api(tags = "Technical card group Rest Controller")
@RequestMapping("/api/technical_card_group")
@RequiredArgsConstructor
public class TechnicalCardGroupRestController {

    private final TechnicalCardGroupService technicalCardGroupService;
    private final CheckEntityService checkEntityService;
    private final TechnicalCardGroupRepository technicalCardGroupRepository;


    @ApiOperation(value = "getAll", notes = "Возвращает список всех групп технических карт")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех групп технических карт"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalCardGroupDto>> getAll() {
        List<TechnicalCardGroupDto> technicalCardGroups = technicalCardGroupService.getAll();
        return ResponseEntity.ok(technicalCardGroups);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную группу технических карт по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа технических карт найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardGroupDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти группу технических карт") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) technicalCardGroupRepository, id);
        return ResponseEntity.ok(technicalCardGroupService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает группу технических карт на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа технических карт успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardGroupDto> create(@ApiParam(name = "technicalCardGroupDto", value = "DTO группы технических карт, которую необходимо создать")
                                                   @RequestBody TechnicalCardGroupDto technicalCardGroupDto) {
        return ResponseEntity.ok().body(technicalCardGroupService.create(technicalCardGroupDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет группу технических карт на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа технических карт успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardGroupDto> update(@ApiParam(name = "technicalCardGRoupDto",
            value = "DTO группы технических карт, c обновленными данными")
                                                   @RequestBody TechnicalCardGroupDto technicalCardGroupDto) {
        return ResponseEntity.ok().body(technicalCardGroupService.update(technicalCardGroupDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет группу технических карт на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа технических карт успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardGroupDto> deleteById(@ApiParam(name = "id",
            value = "ID группы технических карт, которую необходимо удалить")
                                                       @PathVariable(name = "id") Long id) {
        technicalCardGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
