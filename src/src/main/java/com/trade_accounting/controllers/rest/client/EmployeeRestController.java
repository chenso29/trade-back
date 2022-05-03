package src.main.java.com.trade_accounting.controllers.rest.client;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/api/employee")
@Tag(name = "Employee Rest Controller", description = "CRUD операции с работниками")
@Api(tags = "Employee Rest Controller")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final CheckEntityService checkEntityService;
    private final EmployeeRepository employeeRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех работников")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка работников"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> employeeDtos = employeeService.getAll();
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка работников по заданным параметрам")
    public ResponseEntity<List<EmployeeDto>> getAll(
            @And({
                    @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
                    @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
                    @Spec(path = "middleName", params = "middleName", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
                    @Spec(path = "department.name", params = "department", spec = LikeIgnoreCase.class),
                    @Spec(path = "position.name", params = "position", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class)
            }) Specification<Employee> specification) {

        return ResponseEntity.ok(employeeService.search(specification));
    }

    @GetMapping("/pages")
    @ApiOperation(value = "searchWithPages", notes = "Получение списка работников по заданным параметрам постранично")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение страницы работников"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PageDto<EmployeeDto>> searchWithPages(
            @Conjunction(value = {
                    @Or({
                            @Spec(path = "lastName", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "firstName", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "middleName", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "email", params = "search", spec = LikeIgnoreCase.class),
                            @Spec(path = "phone", params = "search", spec = LikeIgnoreCase.class)

                    })
            }, and = {
                    @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
                    @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
                    @Spec(path = "middleName", params = "middleName", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
                    @Spec(path = "roleDto", params = "roleDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class)
            }) Specification<Employee> specification,
            @RequestParam("column") String sortColumn,
            @RequestParam("direction") String sortDirection,
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("rowsLimit") Integer rowsLimit) {
        Pageable pageParams = PageRequest.of(pageNumber - 1, rowsLimit,
                Sort.by(sortDirection.equals("ASCENDING") ?
                                Sort.Direction.ASC : Sort.Direction.DESC,
                        sortColumn));
        return ResponseEntity.ok(employeeService.search(specification, pageParams));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение работника по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Работник найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<EmployeeDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти работника")
                                               @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) employeeRepository, id);
        return ResponseEntity.ok(employeeService.getById(id));
    }


    @PostMapping
    @ApiOperation(value = "create", notes = "Регистрация нового работника")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Работник успешно зарегестрирован"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "employeeDto", value = "DTO работника, который необходимо создать")
                                    @RequestBody EmployeeDto employeeDto) {
        checkEntityService.checkForBadEmployee(employeeDto);
        return ResponseEntity.ok().body(employeeService.create(employeeDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление информации о работнике")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о работнике успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "employeeDto",
            value = "DTO работника, c обновленными данными")
                                    @RequestBody EmployeeDto employeeDto) {
        checkEntityService.checkExists((JpaRepository) employeeRepository, employeeDto.getId());
        checkEntityService.checkForBadEmployee(employeeDto);
        return ResponseEntity.ok().body(employeeService.update(employeeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление работника по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о работнике успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "ID работника, которого необходимо удалить")
                                        @PathVariable(name = "id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    @ApiOperation(value = "getPrincipal", notes = "Получение информации о работнике")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение информации о работнике"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<EmployeeDto> getUserDetails(@AuthenticationPrincipal String email) {
        EmployeeDto employeeDto = employeeService.getByEmail(email);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка работников по заданным параметрам")
    public ResponseEntity<List<EmployeeDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(employeeService.searchByString(search));
    }
}
