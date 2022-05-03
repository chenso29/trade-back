package src.main.java.com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.ContactDto;
import com.trade_accounting.repositories.company.ContactRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.company.ContactService;
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
@Tag(name = "Contact Rest Controller", description = "CRUD  операции с контактами")
@Api(tags = "Contact Rest Controller")
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactRestController {

    private final ContactService contactService;
    private final CheckEntityService checkEntityService;
    private final ContactRepository contactRepository;

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового контакта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контакт создан"),
            @ApiResponse(code = 201, message = "Запрос принят и контакт добавлен"),
            @ApiResponse(code = 404, message = "Данный контакт не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContactDto> create(@ApiParam(name = "contactDto",
            value = "DTO контакта, которого необходимо создать")
                                             @RequestBody ContactDto contactDto) {
        return ResponseEntity.ok().body(contactService.create(contactDto));
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех контактов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение контактов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContactDto>> getAll() {
        List<ContactDto> contactDtos = contactService.getAll();
        return ResponseEntity.ok(contactDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение контакта по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "контакт найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContactDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти контакт")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) contactRepository, id);
        return ResponseEntity.ok(contactService.getById(id));

    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о контакте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о контакте обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о контакте обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "ContactDto", value = "DTO контакта, которую необходимо обновить")
                                    @RequestBody ContactDto contactDto) {
        return ResponseEntity.ok().body(contactService.update(contactDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление контакта по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "контакт удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить контакт")
                                        @PathVariable(name = "id") Long id) {
        contactService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
