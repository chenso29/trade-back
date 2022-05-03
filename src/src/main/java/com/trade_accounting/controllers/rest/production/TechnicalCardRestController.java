package src.main.java.com.trade_accounting.controllers.rest.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.production.TechnicalCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Technical card Rest Controller", description = "CRUD операции с техническими картами")
@Api(tags = "Technical card Rest Controller")
@RequestMapping("/api/technical_card")
@RequiredArgsConstructor
public class TechnicalCardRestController {

    private final TechnicalCardService technicalCardService;
    private final CheckEntityService checkEntityService;
    private final TechnicalCardRepository technicalCardRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех технических карт")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех технических карт"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalCardDto>> getAll() {
        List<TechnicalCardDto> technicalCards = technicalCardService.getAll();
        return ResponseEntity.ok(technicalCards);
    }

    @ApiOperation(value = "search", notes = "Получение списка технических карт по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка технических карт по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalCardDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(technicalCardService.search(value));
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную техническую карту по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая карта найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти техническую карту") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) technicalCardRepository, id);
        return ResponseEntity.ok(technicalCardService.getById(id));
    }

    @ApiOperation(value = "getByTechnicalCardGroupId", notes = "Возвращает технические карты из определенной группы")
    @GetMapping("/tcg/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Технические карты найдены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalCardDto>> getByTechnicalCardGroupId(@ApiParam(name = "id",
            value = "ID группы технических карт переданный в URL по которому необходимо найти технические карты") @PathVariable(name = "id") Long id) {
        List<TechnicalCardDto> technicalCardDtos = technicalCardService.getAllByTechnicalCardGroupId(id);
        return ResponseEntity.ok(technicalCardDtos);
    }

    @ApiOperation(value = "create", notes = "Создает техническую карту на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая карта успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardDto> create(@ApiParam(name = "technicalCardDto", value = "DTO технической карты, которую необходимо создать")
                                             @RequestBody TechnicalCardDto technicalCardDto) {
        return ResponseEntity.ok().body(technicalCardService.create(technicalCardDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет техническую карту на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая карта успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardDto> update(@ApiParam(name = "technicalCardDto",
            value = "DTO технической карты, c обновленными данными")
                                             @RequestBody TechnicalCardDto technicalCardDto) {
        return ResponseEntity.ok().body(technicalCardService.update(technicalCardDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет техническую карту на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая карта успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalCardDto> deleteById(@ApiParam(name = "id",
            value = "ID технической карты, которую необходимо удалить")
                                                 @PathVariable(name = "id") Long id) {
        technicalCardService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchTechnicalCard")
    @ApiOperation(value = "searchTechnicalCard", notes = "Получение списка технических карт по заданным параметрам")
    public ResponseEntity<List<TechnicalCardDto>> getAllFilter(
            @And({
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class),
            }) Specification<TechnicalCard> spec) {
        return ResponseEntity.ok(technicalCardService.search(spec));
    }
}
