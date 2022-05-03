package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.repositories.warehouse.SupplierAccountProductsListRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.SupplierAccountProductsListService;
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
@Tag(name = "Supplier Account Products List Rest Controller", description = "CRUD операции с товарами в счёте поставщика")
@Api(tags = "Supplier Account Products List")
@RequestMapping("/api/supplier-account-products-list")
@RequiredArgsConstructor
public class SupplierAccountProductsListRestController {

    private final SupplierAccountProductsListService supplierAccountProductsListService;
    private final SupplierAccountProductsListRepository supplierAccountProductsListRepository;
    private final CheckEntityService checkEntityService;

    /*@GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка товаров из счёта по заданным параметрам из фильтра")
    public ResponseEntity<List<SupplierAccountProductsListDto>> getAll(
            @And({
                    @Spec(path = "product.name", params = "productDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "amount", params = "amount", spec = Equal.class),
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "product.purchasePrice", params = "costPrice", spec = Equal.class),
                    @Spec(path = "product.description", params = "description", spec = LikeIgnoreCase.class)
            }) Specification<SupplierAccountProductsList> spec) {
        return ResponseEntity.ok(supplierAccountProductsListService.search(spec));
    }*/

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров из счёта")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров из счёта"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SupplierAccountProductsListDto>> getAll() {
        return ResponseEntity.ok(supplierAccountProductsListService.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает товар из счёта по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SupplierAccountProductsListDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти товар в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository)supplierAccountProductsListRepository,id);
        return ResponseEntity.ok(supplierAccountProductsListService.getById(id));
    }

    @ApiOperation(value = "getBySupplierId", notes = "Возвращает список товаров из счёта по Supplier.id")
    @GetMapping("/supplier-account-products-list/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SupplierAccountProductsListDto>> getBySupplierId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<SupplierAccountProductsListDto> supplierAccountProductsListDtos = supplierAccountProductsListService.searchBySupplierId(id);
        return ResponseEntity.ok(supplierAccountProductsListDtos);
    }

    @ApiOperation(value = "create", notes = "Добавляет товар в счёт на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёт успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "supplierAccountProductsListDto",
            value = "DTO товара в счёте, который необходимо создать") @RequestBody SupplierAccountProductsListDto supplierAccountProductsListDto) {
        return ResponseEntity.ok().body(supplierAccountProductsListService.create(supplierAccountProductsListDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет товар в счёте на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте успешно обновлён"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "supplierAccountProductsListDto",
            value = "DTO InvoiceToBuyersListProducts, который необходимо обновить") @RequestBody SupplierAccountProductsListDto invoiceToBuyerListProductsDto) {
        return ResponseEntity.ok().body(supplierAccountProductsListService.update(invoiceToBuyerListProductsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар в счёте на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить товар в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        supplierAccountProductsListService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
