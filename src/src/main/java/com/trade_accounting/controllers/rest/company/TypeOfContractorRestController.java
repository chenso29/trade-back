package src.main.java.com.trade_accounting.controllers.rest.company;


import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import com.trade_accounting.repositories.company.TypeOfContractorRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.TypeOfContractorService;
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
@Tag(name = "Type Of Contractor Rest Controller", description = "CRUD операции со типами контрагентов")
@Api(tags = "Type Of Contractor Rest Controller")
@RequestMapping("/api/typeofcontractor")
@RequiredArgsConstructor
public class TypeOfContractorRestController {

    private final TypeOfContractorService typeOfContractorService;
    private final CheckEntityService checkEntityService;
    private final TypeOfContractorRepository typeOfContractorRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех типов контрагентов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех типов контрагентов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TypeOfContractorDto>> getAll() {
        List<TypeOfContractorDto> typeOfContractorDtos = typeOfContractorService.getAll();
        return ResponseEntity.ok(typeOfContractorDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный тип контрагента по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тип контрагента найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TypeOfContractorDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти тип контрагента",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) typeOfContractorRepository, id);
        return ResponseEntity.ok(typeOfContractorService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает тип контрагента на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тип контрагента успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> create(@ApiParam(name = "typeOfContractorDto",
            value = "DTO типа контрагента, который необходимо создать")
                                    @RequestBody TypeOfContractorDto typeOfContractorDto) {
        return ResponseEntity.ok().body(typeOfContractorService.create(typeOfContractorDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет тип контрагента на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тип контрагента успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> update(@ApiParam(name = "typeOfContractorDto",
            value = "DTO типа контрагента, который необходимо обновить")
                                    @RequestBody TypeOfContractorDto typeOfContractorDto) {
        return ResponseEntity.ok().body(typeOfContractorService.update(typeOfContractorDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет тип контрагента на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тип контрагента успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить тип контрагента",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        typeOfContractorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
