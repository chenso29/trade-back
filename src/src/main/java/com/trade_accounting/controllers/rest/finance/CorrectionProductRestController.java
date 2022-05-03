package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.dto.finance.CorrectionProductDto;
import com.trade_accounting.repositories.finance.CorrectionProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.CorrectionProductService;
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
@Tag(name = "Correction Product Rest Controller", description = "CRUD  операции с товарами, которые ставим на приход или списываем")
@Api(tags = "Correction Product Rest Controller")
@RequestMapping("/api/correction/product")
@RequiredArgsConstructor
public class CorrectionProductRestController {

    private final CorrectionProductService correctionProductService;
    private final CheckEntityService checkEntityService;
    private final CorrectionProductRepository correctionProductRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех оприходованных товаров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение оприходованных товаров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<CorrectionProductDto>> getAll() {
        return ResponseEntity.ok(correctionProductService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение оприходованного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение оприходованного товара по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти оприходованный товар")
                                                 @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionProductRepository, id);
        return ResponseEntity.ok(correctionProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового оприходованного товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходованный товар добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и оприходованный товар добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionProductDto> create(@ApiParam(name = "correctionProductDto",
            value = "DTO оприходованного товара, который необходимо создать")
                                                @RequestBody CorrectionProductDto correctionProductDto) {
        return ResponseEntity.ok(correctionProductService.create(correctionProductDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об оприходованном товаре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об оприходованном товаре добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об оприходованном товаре обновлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionProductDto> update(@ApiParam(name = "correctionProductDto",
            value = "DTO оприходованного товара, который необходимо обновить")
                                                @RequestBody CorrectionProductDto correctionProductDto) {
        return ResponseEntity.ok(correctionProductService.update(correctionProductDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление оприходованного товара по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходованный товар удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionProductDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить оприходованный товар")
                                                    @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionProductRepository, id);
        correctionProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
