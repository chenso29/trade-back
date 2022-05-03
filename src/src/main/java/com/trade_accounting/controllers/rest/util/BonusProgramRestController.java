package src.main.java.com.trade_accounting.controllers.rest.util;


import com.trade_accounting.models.dto.util.BonusProgramDto;
import com.trade_accounting.repositories.util.BonusProgramRepository;
import com.trade_accounting.services.interfaces.util.BonusProgramService;
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

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@RestController
@Tag(name = "Internal Order Rest Controller", description = "CRUD  операции со внутренними заказами")
@Api(tags = "Internal Order Rest Controller")
@RequestMapping("/api/bonusprogram")
@RequiredArgsConstructor
public class BonusProgramRestController {

    private final CheckEntityService checkEntityService;
    private final BonusProgramService bonusProgramService;
    private final BonusProgramRepository bonusProgramRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех бонусных програм")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка бонусных програм"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<BonusProgramDto>> getAll() {
        return ResponseEntity.ok(bonusProgramService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение бонусной программы по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение бонусной программы по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BonusProgramDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти бонусную программу")
                                                    @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) bonusProgramRepository, id);

        return ResponseEntity.ok(bonusProgramService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой бонусной программы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Бонусная программа создана"),
            @ApiResponse(code = 201, message = "Запрос принят и бонусная программа добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BonusProgramDto> create(@ApiParam(name = "bonusProgramDto",
            value = "DTO бонусной программы, которое необходимо создать")
                                                   @RequestBody BonusProgramDto bonusProgramDto) {
        return ResponseEntity.ok(bonusProgramService.create(bonusProgramDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о бонусной программе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о бонусной программе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о бонусной программе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BonusProgramDto> update(@ApiParam(name = "bonusProgramDto",
            value = "DTO бонусной программы, которую необходимо обновить")
                                                   @RequestBody BonusProgramDto bonusProgramDto) {
        return ResponseEntity.ok(bonusProgramService.update(bonusProgramDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление бонусной программы по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Бонусная программа удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<BonusProgramDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить бонусную программу")
                                                       @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) bonusProgramRepository, id);
        bonusProgramService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
