package src.main.java.com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.repositories.retail.RetailMakingRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.retail.RetailMakingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
@Tag(name = "RetilMaking Rest Controller", description = "CRUD операции с внесениями")
@Api(tags = "RetilMaking Rest Controller")
@RequestMapping("/api/making")
@RequiredArgsConstructor
public class RetailMakingRestController {

    private final CheckEntityService checkEntityService;

    private final RetailMakingRepository retailMakingRepository;

    private final RetailMakingService retailMakingService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех внесений")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка внесений"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailMakingDto>> getAll() {
        return ResponseEntity.ok(retailMakingService.getAll());
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка внесений по заданным параметрам")
    public ResponseEntity<List<RetailMakingDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "company.name", params = "company_id", spec = Like.class),
                    @Spec(path = "retailStore.name", params = "retail_store_id", spec = Like.class),
                    @Spec(path = "fromWhom", params = "fromWhom", spec = Like.class),
                    @Spec(path = "comment", params = "comment", spec = Like.class),
                    @Spec(path = "sum", params = "sum", spec = Equal.class),
                    @Spec(path = "isSent", params = "sent", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
            }) Specification<RetailMaking> spec) {
        return ResponseEntity.ok(retailMakingService.search(spec));
    }

    @GetMapping("/search/{search}")
    @ApiOperation(value = "search", notes = "Получение списка внесений по номеру или комментарию")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка внесений по номеру или комментарию"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailMakingDto>> search(@ApiParam(name = "search",
            value = "Переданный в URL search, по которому необходимо найти приемку")
                                                        @PathVariable(name = "search") String search) {
        List<RetailMakingDto> retailMakingDtoList = retailMakingService.search(search);
        return ResponseEntity.ok(retailMakingDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение данных о внесении")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение данных о внесении"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailMakingDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти информацию о внесении")
                                                   @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) retailMakingRepository, id);

        return ResponseEntity.ok(retailMakingService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление внесения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внесение создана"),
            @ApiResponse(code = 201, message = "Запрос принят и внесение добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailMakingDto> create(@ApiParam(name = "payoutDto",
            value = "DTO внесения, которую необходимо создать")
                                                  @RequestBody RetailMakingDto retailMakingDto) {
        return ResponseEntity.ok(retailMakingService.create(retailMakingDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о внесении")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о выплате внесении"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о выплате обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailMakingDto> update(@ApiParam(name = "payoutDto",
            value = "DTO внесения, которую необходимо обновить")
                                                  @RequestBody RetailMakingDto retailMakingDto) {
        return ResponseEntity.ok(retailMakingService.update(retailMakingDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление внесения по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Внесение удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RetailMakingDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить внесение")
                                                      @PathVariable Long id) {

        checkEntityService.checkExists((JpaRepository) retailMakingRepository, id);
        retailMakingService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
