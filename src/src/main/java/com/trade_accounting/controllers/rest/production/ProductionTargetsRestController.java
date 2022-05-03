package src.main.java.com.trade_accounting.controllers.rest.production;

import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.repositories.production.ProductionTargetsRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.production.ProductionTargetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name = "Production_Targets_Rest_Controller", description = "CRUD операции с производственными заданиями")
@Api(tags = "Production_Targets_Rest_Controller")
@RequestMapping("/api/production_targets")
@RequiredArgsConstructor
public class ProductionTargetsRestController {

    private final ProductionTargetsRepository productionTargetsRepository;
    private final ProductionTargetsService productionTargetsService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех производственных заданий")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех производственных заданий"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductionTargetsDto>> getAll() {
        List<ProductionTargetsDto> productionTargets = productionTargetsService.getAll();
        return ResponseEntity.ok(productionTargets);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенное производственное задание по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "производственное задание найдено"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionTargetsDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти производственное задание") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) productionTargetsRepository, id);
        return ResponseEntity.ok(productionTargetsService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает производственное задание на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "производственное задание успешно создано"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionTargetsDto> create(@ApiParam(name = "productionTargetsDto", value = "DTO производственного задания, которую необходимо создать")
                                                        @RequestBody ProductionTargetsDto productionTargetsDto) {
        return ResponseEntity.ok().body(productionTargetsService.create(productionTargetsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет производственное задание на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производственное задание успешно обновлено"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionTargetsDto> update(@ApiParam(name = "productionTargetsDto",
            value = "DTO производственного задания, c обновленными данными")
                                                        @RequestBody ProductionTargetsDto productionTargetsDto) {
        return ResponseEntity.ok().body(productionTargetsService.update(productionTargetsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет производственное задание на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Производственное задание успешно удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductionTargetsDto> deleteById(@ApiParam(name = "id",
            value = "ID производственного задания, которую необходимо удалить")
                                                            @PathVariable(name = "id") Long id) {
        productionTargetsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("searchProductionTargetsDto")
    @ApiOperation(value = "searchProductionTargetsDto", notes = "Получение списков производственных заданий по заданным параметрам")
    public ResponseEntity<List<ProductionTargetsDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "companyId.name", params = "company", spec = Like.class),
                    @Spec(path = "deliveryPlannedMoment", params = "deliveryPlannedMoment", spec = GreaterThanOrEqual.class),
                    @Spec(path = "materialWarehouse", params = "materialWarehouse", spec = Equal.class),
                    @Spec(path = "productionWarehouse", params = "productionWarehouse", spec = Equal.class),
                    @Spec(path = "productionStart", params = "productionStart", spec = GreaterThanOrEqual.class),
                    @Spec(path = "productionEnd", params = "productionEnd", spec = GreaterThanOrEqual.class),
                    @Spec(path = "shared", params = "shared", spec = Equal.class),
                    @Spec(path = "Owner", params = "Owner", spec = GreaterThanOrEqual.class),
                    @Spec(path = "employeeOwner", params = "employeeOwner", spec = GreaterThanOrEqual.class),
                    @Spec(path = "published", params = "published", spec = Equal.class),
                    @Spec(path = "printed", params = "printed", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = GreaterThanOrEqual.class),
                    @Spec(path = "updated", params = "updated", spec = GreaterThanOrEqual.class),
                    @Spec(path = "updatedByName", params = "updatedByName", spec = GreaterThanOrEqual.class)
            }) Specification<ProductionTargets> spec) {
        return ResponseEntity.ok(productionTargetsService.search(spec));
    }

    @ApiOperation(value = "search", notes = "Получение списка производственных заданий по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение производственных заданий по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductionTargetsDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(productionTargetsService.search(value));
    }

}
