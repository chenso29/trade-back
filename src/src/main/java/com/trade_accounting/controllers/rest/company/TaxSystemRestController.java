package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.TaxSystemDto;
import com.trade_accounting.repositories.company.TaxSystemRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.TaxSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Tax System Rest Controller", description = "CRUD операции с налоговыми системами")
@Api(tags = "Tax System Rest Controller")
@RequestMapping("/api/taxsystem")
@RequiredArgsConstructor
public class TaxSystemRestController {

    private final TaxSystemService taxSystemService;
    private final CheckEntityService checkEntityService;
    private final TaxSystemRepository taxSystemRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех налоговых систем")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех налоговых систем"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<TaxSystemDto>> getAll() {
        List<TaxSystemDto> taxSystems = taxSystemService.getAll();
        return ResponseEntity.ok(taxSystems);
    }

    @ApiOperation(value = "getById", notes = "Возвращает налоговую сисему по её Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Налоговая система найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaxSystemDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти налоговую систему")
                                                @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) taxSystemRepository, id);
        return ResponseEntity.ok(taxSystemService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Регистрация новой налоговой системы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Налоговая система успешно зарегистрирована"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping
    public ResponseEntity<?> create(@ApiParam(name = "taxSystemDto",
            value = "DTO налоговой системы, которую необходимо создать")
                                    @RequestBody TaxSystemDto taxSystemDto) {
        return ResponseEntity.ok().body(taxSystemService.create(taxSystemDto));
    }

    @ApiOperation(value = "update", notes = "Обновление информации о налоговой системе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о налоговой системе успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<?> update(@ApiParam(name = "taxSystemDto",
            value = "DTO налоговой системы, которую необходимо обновить")
                                    @RequestBody TaxSystemDto taxSystemDto) {
        return ResponseEntity.ok().body(taxSystemService.update(taxSystemDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление налоговой системы по её id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о налоговой системе успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "ID налоговой системы, которую необходимо удалить")
                                        @PathVariable(name = "id") Long id) {
        taxSystemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
