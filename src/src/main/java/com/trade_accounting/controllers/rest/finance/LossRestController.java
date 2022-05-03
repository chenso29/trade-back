package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.repositories.finance.LossRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.LossService;
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
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
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
@Tag(name = "Loss Rest Controller", description = "CRUD  операции со списаниями")
@Api(tags = "Loss Rest Controller")
@RequestMapping("/api/loss")
@RequiredArgsConstructor
public class LossRestController {

    private final LossRepository lossRepository;
    private final LossService lossService;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех списаний")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка списаний"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<LossDto>> getAll() {
        List<LossDto> lossDtoList;
        lossDtoList = lossService.getAll();
        return ResponseEntity.ok(lossDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списания по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списание найдено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LossDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти списание")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) lossRepository,id);
        return ResponseEntity.ok(lossService.getById(id));
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка списаний по заданным параметрам")
    public ResponseEntity<List<LossDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Like.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "isSend", params = "send", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<Loss> spec) {
        return ResponseEntity.ok(lossService.search(spec));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового списания")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списание создано"),
            @ApiResponse(code = 201, message = "Запрос принят и списание добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LossDto> create(@ApiParam(name = "lossDto", value = "DTO списания, которого необходимо создать")
                                              @RequestBody LossDto lossDto) {
        return ResponseEntity.ok().body(lossService.create(lossDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о списании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о списании обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о списании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "lossDto", value = "DTO списания, которого необходимо обновить")
                                    @RequestBody LossDto lossDto) {
        return ResponseEntity.ok().body(lossService.update(lossDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление списания по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списание удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить списание")
                                        @PathVariable(name = "id") Long id) {
        lossService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину списания по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списание перенесено в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить списание")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) lossRepository, id);
        lossService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление списания по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Списание восстановленно"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить списание")
                                                               @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) lossRepository, id);
        lossService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }

}
