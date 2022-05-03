package src.main.java.com.trade_accounting.controllers.rest.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
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
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController для ProductDto с реализацией ProductService
 *
 * @author Sanych
 * @see ProductService
 */
@RestController
@Tag(name = "Product Rest Controller", description = "CRUD операции с товаром")
@Api(tags = "Product Rest Controller")
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final CheckEntityService checkEntityService;
    private final ProductRepository productRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров (лёгкое дто)")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров (лёгкое дто)"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productGroups = productService.getAll();
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "search", notes = "Получение списка товаров по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров (лёгкое дто)"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(productService.search(value));
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "search", notes = "Получение списка товаров по фильтру")
    public ResponseEntity<List<ProductDto>> getAllByFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "weight", params = "weight", spec = Equal.class),
                    @Spec(path = "volume", params = "volume", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
                    @Spec(path = "purchasePrice", params = "purchasePrice", spec = Equal.class),
            }) Specification<Product> spec) {
        return ResponseEntity.ok(productService.search(spec));
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный товар по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) productRepository, id);
        return ResponseEntity.ok(productService.getById(id));
    }

    @ApiOperation(value = "getByProductGroupId", notes = "Возвращает товары из определенной группы")
    @GetMapping("/pg/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getByProductGroupId(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
        List<ProductDto> productGroups = productService.getAllByProductGroupId(id);
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "getByContractorId", notes = "Возвращает товары из определенной группы")
    @GetMapping("/cnt/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getAllByContractorId(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
        List<ProductDto> productGroups = productService.getAllByContractorId(id);
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "getLiteByProductGroupId", notes = "Возвращает товары из определенной группы (лёгкое дто)")
    @GetMapping("lite/pg/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getLiteByProductGroupId(
            @ApiParam(name = "id", value = "ID группы товаров, переданный в URL, по которому необходимо найти товары")
            @PathVariable(name = "id") Long id) {
        List<ProductDto> productGroups = productService.getAllByProductGroupId(id);
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "create", notes = "Создает товар на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> create(@ApiParam(name = "productDto", value = "DTO товара, который необходимо создать")
                                             @RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.create(productDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет товар на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> update(@ApiParam(name = "productDto",
            value = "DTO товара, c обновленными данными")
                                             @RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.update(productDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> deleteById(@ApiParam(name = "id",
            value = "ID товара, который необходимо удалить")
                                                 @PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pages")
    @ApiOperation(value = "searchWithPages", notes = "Получение списка товаров по заданным параметрам постранично")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение страницы товаров"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PageDto<ProductDto>> searchWithPages(
            @Conjunction(value = {
                    @Or({
                            @Spec(path = "weight", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "volume", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "purchase_price", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "description", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "name", params = "search", spec = LikeIgnoreCase.class)

                    })
            }, and = {
                    @Spec(path = "weight", params = "weight", spec = LikeIgnoreCase.class),
                    @Spec(path = "volume", params = "volume", spec = LikeIgnoreCase.class),
                    @Spec(path = "purchase_price", params = "purchase_price", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "archive", params = "archive", spec = LikeIgnoreCase.class),
                    @Spec(path = "countryOrigin", params = "countryOrigin", spec = LikeIgnoreCase.class),
                    @Spec(path = "minimumBalance", params = "minimumBalance", spec = LikeIgnoreCase.class),
                    @Spec(path = "saleTax", params = "saleTax", spec = LikeIgnoreCase.class)
            }) Specification<Product> specification,
            @RequestParam("column") String sortColumn,
            @RequestParam("direction") String sortDirection,
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("rowsLimit") Integer rowsLimit) {
        Pageable pageParams = PageRequest.of(pageNumber - 1, rowsLimit,
                Sort.by(sortDirection.equals("ASCENDING") ?
                                Sort.Direction.ASC : Sort.Direction.DESC,
                        sortColumn));
        return ResponseEntity.ok(productService.search(specification, pageParams));
    }

}
