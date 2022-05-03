package src.main.java.com.trade_accounting.controllers.rest.client;

import com.trade_accounting.models.dto.client.RoleDto;
import com.trade_accounting.repositories.client.RoleRepository;
import com.trade_accounting.services.interfaces.client.RoleService;
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

@RestController
@Tag(name = "Role Rest Controller", description = "CRUD операции с ролями")
@Api(tags = "Role Rest Controller")
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleRestController {

    private final RoleService roleService;
    private final CheckEntityService checkEntityService;
    private final RoleRepository roleRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех ролей")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех ролей"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roleDtos = roleService.getAll();
        return ResponseEntity.ok(roleDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную роль по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RoleDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти роль",
            example = "1",
            required = true) @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) roleRepository, id);
        return ResponseEntity.ok(roleService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает роль на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "roleDto",
            value = "DTO роли, которую необходимо создать") @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok().body(roleService.create(roleDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет роль на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "roleDto",
            value = "DTO роли, которую необходимо обновить")
                                    @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok().body(roleService.update(roleDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет роль на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<RoleDto> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить роль",
            example = "1",
            required = true
    ) @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) roleRepository, id);
        roleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}