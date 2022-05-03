package src.main.java.com.trade_accounting.controllers.rest.client;

import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.client.DepartmentService;
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
@RequestMapping("/api/department")
@Tag(name = "Department Rest Controller", description = "Контроллер с CRUD операциями для работы с подразделениями")
@Api(tags = "Department Rest Controller")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;
    private final CheckEntityService checkEntityService;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получить список всех подразделений")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка подразделений"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})

    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departmentDtos = departmentService.getAll();
        return ResponseEntity.ok(departmentDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получить информации о подразделении по его Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о подразделении найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})

    public ResponseEntity<DepartmentDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти подразделение",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) departmentRepository, id);
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Создание информации о новом подразделении")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о подразделении успешно добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})

    public ResponseEntity<?> create(@ApiParam(name = "departmentDto", value = "DTO подразделения, который необходимо создать") @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok().body(departmentService.create(departmentDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление данных о подразделении")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о подразделении успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})
    public ResponseEntity<?> update(@ApiParam(name = "departmentDto", value = "DTO подразделения, который необходимо обновить") @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok().body(departmentService.update(departmentDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление информации о подразделении по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о подразделении успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить подразделение",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}