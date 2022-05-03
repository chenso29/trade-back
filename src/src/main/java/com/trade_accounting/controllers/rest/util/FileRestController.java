package src.main.java.com.trade_accounting.controllers.rest.util;

import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.util.FileService;
import com.trade_accounting.utils.mapper.util.FileMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "File Rest Controller", description = "CRUD операции с файлами")
@Api(tags = "File Rest Controller")
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileRestController {

    private final FileMapper fileMapper;
    private final FileService fileService;
    private final CheckEntityService checkEntityService;
    private final FileRepository fileRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех файлов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех фото"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<FileDto>> getAll() {
        List<FileDto> files = fileService.getAll().stream()
                .map(fileMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(files);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка файлов по заданным параметрам")
    public ResponseEntity<List<FileDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "employee", params = "employee", spec = LikeIgnoreCase.class),
                    @Spec(path = "placement", params = "placement", spec = LikeIgnoreCase.class)
            }) Specification<File> spec) {
        return ResponseEntity.ok(fileService.search(spec));
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный файл по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Файл найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<FileDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти файл",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) fileRepository, id);
        return ResponseEntity.ok(fileMapper.toDto(fileService.getById(id)));
    }

    @ApiOperation(value = "create", notes = "Создает информацию о файле и файл на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Файл найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    @PostMapping
    public ResponseEntity<FileDto> create(@ApiParam (name = "FileDto", value = "Dto файла, который необходимо добавить")
                                                @RequestBody FileDto fileDto) {
        return ResponseEntity.ok(fileService.create(fileDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет файл и ифнормацию о нём на основе переданного ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Файл и ифнормацию о нём  успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
