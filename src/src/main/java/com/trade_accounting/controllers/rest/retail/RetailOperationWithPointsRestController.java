package src.main.java.com.trade_accounting.controllers.rest.retail;


import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import com.trade_accounting.repositories.retail.RetailOperationWithPointsRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.retail.RetailOperationWithPointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Tag(name = "RetailOperationWithPointsRest Controller", description = "CRUD операции с операции с баллами")
@Api(tags = "RetailOperationWithPointsRest Rest Controller")
@RequestMapping("/api/operation_with_points")
@RequiredArgsConstructor
public class RetailOperationWithPointsRestController {

    private final RetailOperationWithPointsService retailOperationWithPointsService;
    private final CheckEntityService checkEntityService;
    private final RetailOperationWithPointsRepository retailOperationWithPointsRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех операций с баллами")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех операций с баллами"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailOperationWithPointsDto>> getAll() {
        List<RetailOperationWithPointsDto> retailOperationWithPointsDtos = retailOperationWithPointsService.getAll();
        return ResponseEntity.ok(retailOperationWithPointsDtos);
    }


    @ApiOperation(value = "getById", notes = "Возвращает определенную операцию с баллами по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Операция с баллами найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailOperationWithPointsDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) retailOperationWithPointsRepository, id);
        return ResponseEntity.ok(retailOperationWithPointsService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает операцию с баллами на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Операция с баллами успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailOperationWithPointsDto> create(
            @RequestBody RetailOperationWithPointsDto retailOperationWithPointsDto) {
        return ResponseEntity.ok().body(retailOperationWithPointsService.create(retailOperationWithPointsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет  операцию с баллами на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Операция с баллами успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailOperationWithPointsDto> update(
            @RequestBody RetailOperationWithPointsDto retailOperationWithPointsDto) {
        checkEntityService.checkExists((JpaRepository) retailOperationWithPointsRepository, retailOperationWithPointsDto.getId());
        return ResponseEntity.ok().body(retailOperationWithPointsService.update(retailOperationWithPointsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет  операцию с баллами на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Операция с баллами успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailOperationWithPointsDto> deleteById(@PathVariable("id") Long id) {
        retailOperationWithPointsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
