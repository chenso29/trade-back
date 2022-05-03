package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.AddressDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.services.interfaces.company.AddressService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
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

@RestController
@Tag(name = "Address Rest Controller", description = "CRUD  операции с адресами")
@Api(tags = "Address Rest Controller")
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;
    private final CheckEntityService checkEntityService;
    private final AddressRepository addressRepository;

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "getById", notes = "Получение адреса по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Адрес найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AddressDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти адрес")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) addressRepository, id);
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового адреса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Адрес создан"),
            @ApiResponse(code = 201, message = "Запрос принят и адрес добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "addressDto", value = "DTO адреса, который необходимо создать")
                                    @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok().body(addressService.create(addressDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение адреса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Адрес изменен"),
            @ApiResponse(code = 201, message = "Запрос принят и адрес обновлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "addressDto", value = "DTO адреса, который необходимо обновить")
                                    @RequestBody AddressDto addressDto) {
        checkEntityService.checkExists((JpaRepository) addressRepository, addressDto.getId());
        return ResponseEntity.ok().body(addressService.update(addressDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление адреса по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Адрес удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить адрес")
                                        @PathVariable(name = "id") Long id) {
        addressService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
