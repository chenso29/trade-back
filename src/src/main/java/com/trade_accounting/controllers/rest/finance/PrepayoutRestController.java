package src.main.java.com.trade_accounting.controllers.rest.finance;


import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
import com.trade_accounting.repositories.finance.PrepayoutRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.PrepayoutService;
import io.swagger.annotations.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Prepayout Rest Controller", description = "CRUD  операции с предоплатами")
@Api(tags = "Prepayout Rest Controller")
@RequestMapping("/api/prepayout")
@RequiredArgsConstructor
public class PrepayoutRestController {
    private final PrepayoutService prepayoutService;
    private final CheckEntityService checkEntityService;
    private final PrepayoutRepository prepayoutRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех предоплат")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка предоплат"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PrepayoutDto>> getAll() {
        List<PrepayoutDto> prepayoutDtoList;
        prepayoutDtoList = prepayoutService.getAll();
        return ResponseEntity.ok(prepayoutDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение предоплаты по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Предоплата найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PrepayoutDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат")
                                                   @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) prepayoutRepository, id);
        return ResponseEntity.ok(prepayoutService.getById(id));
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка счетов по заданным параметрам")
    public ResponseEntity<List<PrepayoutDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = GreaterThanOrEqual.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Like.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "sum", params = "sum", spec = Equal.class),
                    @Spec(path = "isSent", params = "sent", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<Prepayout> spec) {
        return ResponseEntity.ok(prepayoutService.search(spec));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового возврата")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Предоплата создана"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PrepayoutDto> create(@ApiParam(name = "prepayoutDto", value = "DTO возврата, которую необходимо создать")
                                                  @RequestBody PrepayoutDto prepayoutDto) {
        return ResponseEntity.ok().body(prepayoutService.create(prepayoutDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об отгрузке")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о возврате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "buyersReturnDto", value = "DTO возврата, которую необходимо обновить")
                                    @RequestBody PrepayoutDto prepayoutDto) {
        return ResponseEntity.ok().body(prepayoutService.update(prepayoutDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление возврата по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Предоплата удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить предоплату")
                                        @PathVariable(name = "id") Long id) {
        prepayoutService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
