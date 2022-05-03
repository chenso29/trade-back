package src.main.java.com.trade_accounting.controllers.rest.purchases;


import com.trade_accounting.models.dto.purchases.PurchaseHistoryOfSalesDto;
import com.trade_accounting.repositories.purchases.PurchaseHistoryOfSalesRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.purchases.PurchaseHistoryOfSalesService;
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
@Tag(name = "Purchase History Of Sales Rest Controller", description = "CRUD операции с историями продаж")
@Api(tags = "Purchase History Of Sales Rest Controller")
@RequestMapping("/api/purchasehistory")
@RequiredArgsConstructor
public class PurchaseHistoryOfSalesRestController {
    private final CheckEntityService checkEntityService;
    private final PurchaseHistoryOfSalesService purchaseHistoryOfSalesService;
    private final PurchaseHistoryOfSalesRepository purchaseHistoryOfSalesRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех историй продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка историй продаж"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<PurchaseHistoryOfSalesDto>> getAll() {
        return ResponseEntity.ok(purchaseHistoryOfSalesService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение истории продаж по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение истории продаж по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseHistoryOfSalesDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти закупку")
                                                      @PathVariable(name = "id") Long id) {

        checkEntityService.checkExists((JpaRepository) purchaseHistoryOfSalesRepository, id);
        return ResponseEntity.ok(purchaseHistoryOfSalesService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой истории продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "История продаж создана"),
            @ApiResponse(code = 201, message = "Запрос принят и история продаж добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseHistoryOfSalesDto> create(@ApiParam(name = "PurchaseHistoryOfSalesDto",
            value = "DTO истории продаж, которую необходимо создать")
                                                     @RequestBody PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto) {
        return ResponseEntity.ok(purchaseHistoryOfSalesService.create(purchaseHistoryOfSalesDto));
    }



    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об истории продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об истории продаж обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об истории продаж обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseHistoryOfSalesDto> update(@ApiParam(name = "PurchaseHistoryOfSalesDto",
            value = "DTO истории продаж, которую необходимо обновить")
                                                     @RequestBody PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto) {
        return ResponseEntity.ok(purchaseHistoryOfSalesService.update(purchaseHistoryOfSalesDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление истории продаж по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "История продаж удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseHistoryOfSalesDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить историю продаж")
                                                         @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) purchaseHistoryOfSalesRepository, id);
        purchaseHistoryOfSalesService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
