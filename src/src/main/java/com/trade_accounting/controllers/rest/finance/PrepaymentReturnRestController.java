package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
import com.trade_accounting.repositories.finance.PrepaymentReturnRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.finance.PrepaymentReturnService;
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
@Tag(name = "PrepaymentReturn Rest Controller", description = "CRUD  операции с возвратами предоплат")
@Api(tags = "PrepaymentReturn Rest Controller")
@RequestMapping("/api/prepaymentreturn")
@RequiredArgsConstructor
public class PrepaymentReturnRestController {

    private final PrepaymentReturnService prepaymentReturnService;
    private final PrepaymentReturnRepository prepaymentReturnRepository;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов предоплат")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка возвратов предоплат"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PrepaymentReturnDto>> getAll() {
        List<PrepaymentReturnDto> prepaymentReturnDtos = prepaymentReturnService.getAll();
        return ResponseEntity.ok(prepaymentReturnDtos);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка возвратов предоплат по заданным параметрам")
    public ResponseEntity<List<PrepaymentReturnDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "time", params = "time", spec = GreaterThanOrEqual.class),
                    @Spec(path = "company.name", params = "companyDto", spec = Like.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "retailStore.name", params = "retailStoreDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "sent", params = "sent", spec = Equal.class),
                    @Spec(path = "printed", params = "printed", spec = Equal.class),
                    @Spec(path = "comment", params = "comment", spec = Equal.class),
            }) Specification<PrepaymentReturn> spec) {
        return ResponseEntity.ok(prepaymentReturnService.search(spec));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение возврата предоплпаты по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат предоплаты найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PrepaymentReturnDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат предоплаты")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) prepaymentReturnRepository, id);
        return ResponseEntity.ok(prepaymentReturnService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового возврата предоплаты")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ворврат предоплаты создана"),
            @ApiResponse(code = 201, message = "Запрос принят и возврат предоплаты добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PrepaymentReturnDto> create(@ApiParam(name = "prepaymentReturnDto", value = "DTO возврата предоплаты, которго необходимо создать")
                                             @RequestBody PrepaymentReturnDto prepaymentReturnDto) {
        return ResponseEntity.ok().body(prepaymentReturnService.create(prepaymentReturnDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о возврате предоплаты")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о возврате предоплаты обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о возврате предоплаты обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "prepaymentReturnDto", value = "DTO возврата предоплаты, которую необходимо обновить")
                                    @RequestBody PrepaymentReturnDto prepaymentReturnDto) {
        return ResponseEntity.ok().body(prepaymentReturnService.update(prepaymentReturnDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление возврата предоплаты по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат предоплаты удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить возврат предоплаты")
                                        @PathVariable(name = "id") Long id) {
        prepaymentReturnService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
