package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.repositories.finance.CorrectionRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.CorrectionService;
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
@Tag(name = "Correction Rest Controller", description = "CRUD  операции с оприходованием")
@Api(tags = "Correction Rest Controller")
@RequestMapping("/api/correction")
@RequiredArgsConstructor
public class CorrectionRestController {

    private final CorrectionService correctionService;
    private final CheckEntityService checkEntityService;
    private final CorrectionRepository correctionRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех оприходований")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка оприходований"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<CorrectionDto>> getAll() {
        return ResponseEntity.ok(correctionService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списка всех оприходований")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка оприходований"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти оприходование")
                                                     @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionRepository, id);

        return ResponseEntity.ok(correctionService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового оприходования")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование создано"),
            @ApiResponse(code = 201, message = "Запрос принят и оприходование добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionDto> create(@ApiParam(name = "correctionDto",
            value = "DTO оприходования, которое необходимо создать")
                                                    @RequestBody CorrectionDto correctionDto) {
        return ResponseEntity.ok(correctionService.create(correctionDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об оприходовании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об оприходовании обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об оприходовании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionDto> update(@ApiParam(name = "correctionDto",
            value = "DTO оприходования, которую необходимо обновить")
                                                    @RequestBody CorrectionDto correctionDto) {
        return ResponseEntity.ok(correctionService.update(correctionDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление оприходования по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CorrectionDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить оприходование")
                                                        @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionRepository, id);
        correctionService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка счетов  по заданным параметрам")
    public ResponseEntity<List<CorrectionDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Equal.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = Equal.class)
            }) Specification<Correction> spec) {
        return ResponseEntity.ok(correctionService.search(spec));
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину оприходования по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование перенесено в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить оприходование")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionRepository, id);
        correctionService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление оприходования по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование восстановленно"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить оприходование")
                                                               @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) correctionRepository, id);
        correctionService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }

}

