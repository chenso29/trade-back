package src.main.java.com.trade_accounting.controllers.rest.util;

import com.trade_accounting.models.dto.util.ImageDto;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.util.ImageService;
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
@Tag(name = "Image Rest Controller", description = "CRUD операции с фото")
@Api(tags = "Image Rest Controller")
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService imageService;
    private final CheckEntityService checkEntityService;
    private final ImageRepository imageRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех фото")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех фото"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ImageDto>> getAll() {
        List<ImageDto> images = imageService.getAll();
        return ResponseEntity.ok(images);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенное фото по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Фото найдено"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )

    public ResponseEntity<ImageDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти фото",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) imageRepository, id);
        return ResponseEntity.ok(imageService.getById(id));
    }

    @ApiOperation(value = "creatingNewImage", notes = "Добавляет в базу новое изображение")
    @PostMapping
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Фото успешно добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 200, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ImageDto> create(@ApiParam (name = "ImageDto", value = "Dto изображения, которое необходимо добавить")
                                               @RequestBody ImageDto dto){
        return ResponseEntity.ok(imageService.create(dto));
    }

    @ApiOperation(value = "UpdatingNewImage", notes = "Обновляет существующее изображение")
    @PutMapping
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Фото успешно обновлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 200, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ImageDto> update (@ApiParam (name = "ImageDto", value = "Dto изображения, которое необходимо обновить")
                                           @RequestBody ImageDto dto){
        checkEntityService.checkExists((JpaRepository) imageRepository, dto.getId());
        return ResponseEntity.ok(imageService.update(dto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет фото на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Фото успешно удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить фото",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        imageService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}