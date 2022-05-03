package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.RemainDto;
import com.trade_accounting.repositories.warehouse.RemainRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.RemainService;
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
@Tag(name = "Remain Rest Controller", description = "CRUD операции с остатками")
@Api(tags = "Remain Rest Controller")
@RequestMapping("/api/remain")
@RequiredArgsConstructor
public class RemainRestController {
    private final RemainService remainService;
    private final CheckEntityService checkEntityService;
    private final RemainRepository remainRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех остатков")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка остатков"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RemainDto>> getAll() {
        List<RemainDto> remains = remainService.getAll();
        return ResponseEntity.ok(remains);
    }

    @ApiOperation(value = "getById", notes = "Возвращает остаток по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Остаток найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RemainDto> getById(@ApiParam(name = "id",
            value = "переданный в URL ID, по которому необходимо найти остаток")
                                                 @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) remainRepository, id);
        return ResponseEntity.ok(remainService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает остаток на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Остаток успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RemainDto> create(@ApiParam(name = "remainDto", value = "DTO остатка, который необходимо создать")
                                                   @RequestBody RemainDto remainDto) {
        return ResponseEntity.ok().body(remainService.create(remainDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет остаток на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Остаток успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RemainDto> update(@ApiParam(name = "remainDto",
            value = "DTO остатка c обновленными данными")
                                                   @RequestBody RemainDto remainDto) {
        return ResponseEntity.ok().body(remainService.update(remainDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет остаток на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Остаток успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RemainDto> deleteById(@ApiParam(name = "id",
            value = "ID остатка, который необходимо удалить")
                                                       @PathVariable(name = "id") Long id) {
        remainService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
