package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.dto.finance.LossProductDto;
import com.trade_accounting.repositories.finance.LossProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.LossProductService;
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
@Tag(name = "LossProduct Rest Controller", description = "CRUD  операции со списаннами продуктами")
@Api(tags = "LossProduct Rest Controller")
@RequestMapping("/api/lossproduct")
@RequiredArgsConstructor
public class LossProductRestController {

    private final LossProductRepository lossRepository;
    private final LossProductService lossService;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех списанных продуктов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка списанных продуктов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<LossProductDto>> getAll() {
        List<LossProductDto> lossDtoList;
        lossDtoList = lossService.getAll();
        return ResponseEntity.ok(lossDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списанного продукта по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списанный продукт найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LossProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти списанный продукт")
                                           @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) lossRepository,id);
        return ResponseEntity.ok(lossService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового списанного продукта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списанный продукт создан"),
            @ApiResponse(code = 201, message = "Запрос принят и списание добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LossProductDto> create(@ApiParam(name = "lossDto", value = "DTO списанного продукта, которого необходимо создать")
                                          @RequestBody LossProductDto lossDto) {
        return ResponseEntity.ok().body(lossService.create(lossDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о списанном продукте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о списанном продукте обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о списании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "lossDto", value = "DTO списанного продукта, которого необходимо обновить")
                                    @RequestBody LossProductDto lossDto) {
        return ResponseEntity.ok().body(lossService.update(lossDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление списанного продукта по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списанный продукт удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить списанный продукт")
                                        @PathVariable(name = "id") Long id) {
        lossService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
