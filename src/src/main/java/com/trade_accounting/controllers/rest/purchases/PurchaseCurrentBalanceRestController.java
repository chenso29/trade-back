package src.main.java.com.trade_accounting.controllers.rest.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import com.trade_accounting.models.dto.purchases.PurchaseHistoryOfSalesDto;
import com.trade_accounting.repositories.purchases.PurchaseCurrentBalanceRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.purchases.PurchaseCurrentBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Purchase Current Balance Rest Controller", description = "CRUD операции с текущим остатком")
@Api(tags = "Purchase Current Balance Rest Controller")
@RequestMapping("/api/purchase_current_balance")
@RequiredArgsConstructor
public class PurchaseCurrentBalanceRestController {
    private final CheckEntityService checkEntityService;
    private final PurchaseCurrentBalanceService purchaseCurrentBalanceService;
    private final PurchaseCurrentBalanceRepository purchaseCurrentBalanceRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех текущих остатков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка текущих остатков"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<PurchaseCurrentBalanceDto>> getAll() {
        return ResponseEntity.ok(purchaseCurrentBalanceService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение текущего остатка по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение текущего остатка по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PurchaseCurrentBalanceDto > getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти остаток")
                                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) purchaseCurrentBalanceRepository, id);
        return ResponseEntity.ok(purchaseCurrentBalanceService.getById(id));
    }

    public ResponseEntity<PurchaseCurrentBalanceDto> create() {
        return null;
    }

    public ResponseEntity<PurchaseHistoryOfSalesDto> update() {
        return null;
    }

    public ResponseEntity<PurchaseHistoryOfSalesDto> deleteById() {
        return null;
    }
}
