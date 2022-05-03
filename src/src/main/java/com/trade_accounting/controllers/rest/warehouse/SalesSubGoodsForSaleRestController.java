package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import com.trade_accounting.repositories.warehouse.SalesSubGoodsForSaleRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.SalesSubGoodsForSaleService;
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
@Tag(name = "SalesSubGoodsForSale Rest Controller", description = "CRUD операции с товарами на реализации")
@Api(tags = "SalesSubGoodsForSale Rest Controller")
@RequestMapping("api/goodsForSale")
@RequiredArgsConstructor
public class SalesSubGoodsForSaleRestController {

    private final CheckEntityService checkEntityService;
    private final SalesSubGoodsForSaleService salesSubGoodsForSaleService;
    private final SalesSubGoodsForSaleRepository salesSubGoodsForSaleRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров на реализации")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров на реализации"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SalesSubGoodsForSaleDto>> getAll() {
        List<SalesSubGoodsForSaleDto> salesSubGoodsForSaleDtos = salesSubGoodsForSaleService.getAll();
        return ResponseEntity.ok(salesSubGoodsForSaleDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает товары на реализации по ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товары на реализации найдены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesSubGoodsForSaleDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти товары на реализации",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) salesSubGoodsForSaleRepository, id);
        return ResponseEntity.ok(salesSubGoodsForSaleService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает товары на реализации на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товары на реализации успешно добавлены"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesSubGoodsForSaleDto> create(@ApiParam(name = "salesSubGoodsForSaleDto",
            value = "DTO товаров на реализации, которую необходимо создать") @RequestBody SalesSubGoodsForSaleDto salesDto) {
        return ResponseEntity.ok().body(salesSubGoodsForSaleService.create(salesDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет остаток на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товары на реализации успешно обновлены"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesSubGoodsForSaleDto> update(@ApiParam(name = "salesSubGoodsForSaleDto",
            value = "DTO остатка, который необходимо обновить") @RequestBody SalesSubGoodsForSaleDto salesDto) {
        checkEntityService.checkExists((JpaRepository) salesSubGoodsForSaleRepository, salesDto.getId());
        return ResponseEntity.ok().body(salesSubGoodsForSaleService.update(salesDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товары на реализациим на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товары на реализации успешно удалены"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Long> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить товары на реализации",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        salesSubGoodsForSaleService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка товаров на реализации по фильтру")
    public ResponseEntity<List<SalesSubGoodsForSaleDto>> searchByFilter(
            @And({
                    @Spec(path = "product.id", params = "productId", spec = Equal.class),
                    @Spec(path = "code", params = "code", spec = Equal.class),
                    @Spec(path = "vendorCode", params = "vendorCode", spec = Equal.class),
                    @Spec(path = "transferred", params = "transferred", spec = Equal.class),
                    @Spec(path = "accepted", params = "accepted", spec = Equal.class),
            }) Specification<SalesSubGoodsForSale> spec) {
        return ResponseEntity.ok(salesSubGoodsForSaleService.search(spec));
    }
}
