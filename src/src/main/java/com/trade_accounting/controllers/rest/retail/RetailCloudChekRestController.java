package src.main.java.com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.repositories.retail.RetailCloudCheckRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.retail.RetailCloudCheckService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "RetailCloudCheck Rest Controller", description = "CRUD операции с очередями облачных чеков")
@Api(tags = "RetailCloudCheck Rest Controller")
@RequestMapping("/api/cloudcheck")
@RequiredArgsConstructor
public class RetailCloudChekRestController {
    private final CheckEntityService checkEntityService;

    private final RetailCloudCheckRepository retailCloudCheckRepository;

    private final RetailCloudCheckService retailCloudCheckService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех очередей облачный чеков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка очередей облачный чеков"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailCloudCheckDto>> getAll() {
        return ResponseEntity.ok(retailCloudCheckService.getAll());
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка очередей облачный чеков по заданным параметрам")
    public ResponseEntity<List<RetailCloudCheckDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "initiator.name", params = "retailStoreDto", spec = Like.class),
                    @Spec(path = "fiscalizationPoint.name", params = "retailStoreDto", spec = Like.class),
                    @Spec(path = "currency.name", params = "currencyDto", spec = Like.class),
                    @Spec(path = "cashier.name", params = "EmployeeDto", spec = Like.class),
                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "checkStatus", params = "checkStatus", spec = Equal.class),
                    @Spec(path = "total", params = "total", spec = Equal.class),
            }) Specification<RetailCloudCheck> spec) {
        return ResponseEntity.ok(retailCloudCheckService.search(spec));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение данных об очередях облачный чеков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение данных об очередях облачный чеков"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailCloudCheckDto> getById(@ApiParam(name = "id" , type = "Long",
            value = "Переданный в URL id, по которому необходимо найти информацию об очереди облачных чеков")
                                                   @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) retailCloudCheckRepository, id);

        return ResponseEntity.ok(retailCloudCheckService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление очереди облачных чеков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Очередь облачных чеков создана"),
            @ApiResponse(code = 201, message = "Запрос принят и внесение добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailCloudCheckDto> create(@ApiParam(name = "payoutDto",
            value = "DTO внесения, которую необходимо создать")
                                                  @RequestBody RetailCloudCheckDto retailCloudCheckDto) {
        return ResponseEntity.ok(retailCloudCheckService.create(retailCloudCheckDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об очереди облачных чеков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об очереди облачных чеков"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о выплате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailCloudCheckDto> update(@ApiParam(name = "payoutDto",
            value = "DTO очереди облачных чеков, которую необходимо обновить")
                                                  @RequestBody RetailCloudCheckDto retailCloudCheckDto) {
        return ResponseEntity.ok(retailCloudCheckService.update(retailCloudCheckDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление очереди облачных чеков по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Очередь облачных чеков удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailCloudCheckDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить очередь облачных чеков")
                                                      @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) retailCloudCheckRepository, id);
        retailCloudCheckService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
