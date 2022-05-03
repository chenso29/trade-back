package src.main.java.com.trade_accounting.controllers.rest.units;


import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.repositories.units.CurrencyRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.units.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Currency Rest Controller", description = "CRUD операции с валютами")
@Api(tags = "Currency Rest Controller")
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyService currencyService;
    private final CheckEntityService checkEntityService;
    private final CurrencyRepository currencyRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех валют")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех валют"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<CurrencyDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает определённую валюту по ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CurrencyDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти валюту",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) currencyRepository, id);
        return ResponseEntity.ok(currencyService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает валюту на основе передданых данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CurrencyDto> create(@ApiParam(name = "currencyDto",
            value = "DTO валюты, которую необходимо создать") @RequestBody CurrencyDto currencyDto) {
        return ResponseEntity.ok().body(currencyService.create(currencyDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет валюту на основе передданых данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CurrencyDto> update(@ApiParam(name = "currencyDto",
            value = "DTO валюты, которую необходимо обновить") @RequestBody CurrencyDto currencyDto) {
        checkEntityService.checkExists((JpaRepository) currencyRepository, currencyDto.getId());
        return ResponseEntity.ok().body(currencyService.update(currencyDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет валюту на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Long> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить валюту",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        currencyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка валют по заданным параметрам")
    public ResponseEntity<List<CurrencyDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "shortName", params = "shortName", spec = LikeIgnoreCase.class),
                    @Spec(path = "fullName", params = "fullName", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = Like.class),
                    @Spec(path = "digitalCode", params = "digitalCode", spec = Like.class),
                    @Spec(path = "letterCode", params = "letterCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = Like.class),
            }) Specification<Currency> spec) {
        return ResponseEntity.ok(currencyService.search(spec));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка работников по заданным параметрам")
    public ResponseEntity<List<CurrencyDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(currencyService.searchByString(search));
    }
}
