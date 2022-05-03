package src.main.java.com.trade_accounting.controllers.rest.production;

import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import com.trade_accounting.services.interfaces.production.TechnicalProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Technical Process Rest Controller", description = "CRUD операции с тех.процессами")
@Api(tags = "Technical Process Rest Controller")
@RequestMapping("/api/technical/process")
@RequiredArgsConstructor
public class TechnicalProcessRestController {

    private final TechnicalProcessService technicalProcessService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех тех.процессов")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех тех.процессов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalProcessDto>> getAll() {
        List<TechnicalProcessDto> technicalProcess = technicalProcessService.getAll();
        return ResponseEntity.ok(technicalProcess);
    }

    @ApiOperation(value = "search", notes = "Получение списка тех.процессов по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка тех.процессов по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalProcessDto>> getAllBySearch(@RequestParam("query") String value) {
        return ResponseEntity.ok(technicalProcessService.search(value));
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный тех.процесс по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тех.процесс найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalProcessDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти тех.процесс") @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(technicalProcessService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает техническую операцию на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая операция успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalProcessDto> create(@ApiParam(name = "technicalOperationsDto",
            value = "DTO тех.процесса, которую необходимо создать") @RequestBody TechnicalProcessDto technicalProcessDto) {
        return ResponseEntity.ok().body(technicalProcessService.create(technicalProcessDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет тех.процесс на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тех.процесс успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalProcessDto> update(@ApiParam(name = "technicalProcessDto",
            value = "DTO тех.процесса, c обновленными данными") @RequestBody TechnicalProcessDto technicalProcessDto) {
        return ResponseEntity.ok().body(technicalProcessService.update(technicalProcessDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет тех.процесс на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тех.процесс успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalProcessDto> deleteById(@ApiParam(name = "id",
            value = "ID тех.процееса, который необходимо удалить") @PathVariable(name = "id") Long id) {
        technicalProcessService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("searchTechnicalProcess")
    @ApiOperation(value = "searchTechnicalProcess", notes = "Получение списка тех процессов по заданным параметрам")
    public ResponseEntity<List<TechnicalProcessDto>> getAllFilter (
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = Equal.class),
                    @Spec(path = "dateOfChanged", params = "dateOfChanged", spec = Equal.class)
            }) Specification<TechnicalProcess> spec) {
                return ResponseEntity.ok(technicalProcessService.search(spec));
    }
}
