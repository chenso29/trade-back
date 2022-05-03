package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.ContractService;
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
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
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
@Tag(name = "Contract Rest Controller", description = "CRUD  операции с договорами")
@Api(tags = "Contract Rest Controller")
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractRestController {

    private final ContractService contractService;
    private final CheckEntityService checkEntityService;
    private final ContractRepository contractRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех договоров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка договоров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractDto>> getAll() {
        List<ContractDto> contracts = contractService.getAll();
        return ResponseEntity.ok(contracts);
    }


    @GetMapping("/search/{searchContr}")
    @ApiOperation(value = "searchTerm", notes = "Получение списка некоторых договоров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение отф. списка договоров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractDto>> getAll(@ApiParam(name = "searchContr",
            value = "Переданный в URL searchTerm, по которому необходимо найти договор")
                                                    @PathVariable(name = "searchContr") String searchContr) {
        List<ContractDto> contractDtoList = contractService.getAll(searchContr);
        return ResponseEntity.ok(contractDtoList);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка договоров по заданным параметрам")
    public ResponseEntity<List<ContractDto>> search(
            @Conjunction(value = {
                    @Or({@Spec(path = "bankAccount.bank", params = "bankAccount", spec = LikeIgnoreCase.class),
                            @Spec(path = "bankAccount.account", params = "bankAccount", spec = Like.class)}),

                    @Or({@Spec(path = "legalDetail.firstName", params = "legalDetails", spec = LikeIgnoreCase.class),
                            @Spec(path = "legalDetail.lastName", params = "legalDetails", spec = LikeIgnoreCase.class),
                            @Spec(path = "legalDetail.middleName", params = "legalDetails", spec = LikeIgnoreCase.class)
                    }
                    )},
                    and = {
                            @Spec(path = "id", params = "id", spec = Equal.class),
                            @Spec(path = "amount", params = "amount", spec = Equal.class),
                            @Spec(path = "contractor.name", params = "contractor", spec = LikeIgnoreCase.class),
                            @Spec(path = "contractDate", params = "contractDate", spec = Equal.class),
                            @Spec(path = "company.name", params = "company", spec = LikeIgnoreCase.class),
                            @Spec(path = "archive", params = "archive", spec = Equal.class),
                            @Spec(path = "number", params = "number", spec = Like.class),
                            @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class)
                    }) Specification<Contract> specification
    ) {
        return ResponseEntity.ok(contractService.search(specification));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение договора по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти договор")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) contractRepository, id);
        return ResponseEntity.ok(contractService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового договора")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор создан"),
            @ApiResponse(code = 201, message = "Запрос принят и договор добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> create(@ApiParam(name = "contractDto",
            value = "DTO договора, который необходимо создать")
                                              @RequestBody ContractDto contractDto) {
        return ResponseEntity.ok().body(contractService.create(contractDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о договоре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о договоре обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о договоре обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> update(@ApiParam(name = "contractDto",
            value = "DTO договора, который необходимо обновить")
                                              @RequestBody ContractDto contractDto) {
        return ResponseEntity.ok().body(contractService.update(contractDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление договора по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить договор")
                                                  @PathVariable(name = "id") Long id) {
        contractService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
