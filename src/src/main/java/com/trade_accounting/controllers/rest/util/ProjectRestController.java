package src.main.java.com.trade_accounting.controllers.rest.util;

import com.trade_accounting.models.dto.util.ProjectDto;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.util.ProjectService;
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
@Tag(name = "Project Rest Controller", description = "CRUD операции с проектами")
@Api(tags = "Project Rest Controller")
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;
    private final CheckEntityService checkEntityService;
    private final ProjectRepository projectRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех проектов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех проектов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<ProjectDto> projectDtoList = projectService.getAll();
        return ResponseEntity.ok(projectDtoList);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный проект по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Проект найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProjectDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти проект") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) projectRepository, id);
        return ResponseEntity.ok(projectService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает проект на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Проект успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProjectDto> create(@ApiParam(name = "projectDto", value = "DTO проекта, который необходимо создать")
                                             @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok().body(projectService.create(projectDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет проект на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Проект успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProjectDto> update(@ApiParam(name = "projectDto",
            value = "DTO проекта, c обновленными данными")
                                             @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok().body(projectService.update(projectDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет проект на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Проект успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProjectDto> deleteById(@ApiParam(name = "id",
            value = "ID проекта, который необходимо удалить")
                                                 @PathVariable(name = "id") Long id) {
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
