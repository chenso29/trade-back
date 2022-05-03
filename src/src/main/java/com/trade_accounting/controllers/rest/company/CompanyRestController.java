package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
@Tag(name = "Company Rest Controller", description = "CRUD  операции с компаниями")
@Api(tags = "Company Rest Controller")
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;
    private final CheckEntityService checkEntityService;
    private final CompanyRepository companyRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех компаний")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка компаний"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<CompanyDto>> getAll() {
        List<CompanyDto> companyDtos = companyService.getAll();
        return ResponseEntity.ok(companyDtos);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка компаний по заданным параметрам")
    public ResponseEntity<List<CompanyDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "inn", params = "inn", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
                    @Spec(path = "fax", params = "fax", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "payerVat", params = "payerVat", spec = Equal.class),
                   // @Spec(path = "address", params = "address", spec = LikeIgnoreCase.class),
                    @Spec(path = "commentToAddress", params = "commentToAddress", spec = LikeIgnoreCase.class),
                    @Spec(path = "leader", params = "leader", spec = LikeIgnoreCase.class),
                    @Spec(path = "leaderManagerPosition", params = "leaderManagerPosition", spec = LikeIgnoreCase.class),
                    @Spec(path = "leaderSignature", params = "leaderSignature", spec = LikeIgnoreCase.class),
                    @Spec(path = "chiefAccountant", params = "chiefAccountant", spec = LikeIgnoreCase.class),
                    @Spec(path = "chiefAccountantSignature", params = "chiefAccountantSignature", spec = LikeIgnoreCase.class),
                    @Spec(path = "stamp", params = "stamp", spec = LikeIgnoreCase.class)
            }) Specification<Company> spec) {
        return ResponseEntity.ok(companyService.search(spec));
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "getById", notes = "Получение компании по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Компания найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CompanyDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти компанию")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) companyRepository, id);
        return ResponseEntity.ok(companyService.getById(id));
    }

    @GetMapping("/{email:^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$}")
    @ApiOperation(value = "getByEmail", notes = "Получение компании по ее email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Компания найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CompanyDto> getByEmail(@ApiParam(name = "email", type = "String",
            value = "Переданный в URL email по которому необходимо найти компанию", required = true)
                                                 @PathVariable(name = "email") String email) {
        CompanyDto companyDto = companyService.getByEmail(email);
        return ResponseEntity.ok(companyDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение новой компании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт компании создан"),
            @ApiResponse(code = 201, message = "Запрос принят и компания добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "companyDto", value = "DTO компании, которую необходимо создать")
                                    @RequestBody CompanyDto companyDto) {
        checkEntityService.checkForBadCompany(companyDto);
        return ResponseEntity.ok().body(companyService.create(companyDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о компании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о компании обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о компании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "companyDto", value = "DTO компании, которую необходимо обновить")
                                    @RequestBody CompanyDto companyDto) {
        checkEntityService.checkExists((JpaRepository) companyRepository, companyDto.getId());
        checkEntityService.checkForBadCompany(companyDto);
        return ResponseEntity.ok().body(companyService.update(companyDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление компании по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт компании удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить компанию")
                                        @PathVariable(name = "id") Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
