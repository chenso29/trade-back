package src.main.java.com.trade_accounting.controllers.rest.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import com.trade_accounting.repositories.purchases.PurchaseForecastRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.purchases.PurchaseForecastService;
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
@Tag(name = "Purchase Forecast Rest Controller", description = "CRUD операции с прогнозом")
@Api(tags = "Purchase Forecast Rest Controller")
@RequestMapping("/api/purchase_forecast")
@RequiredArgsConstructor
public class PurchaseForecastRestController {
    private final CheckEntityService checkEntityService;
    private final PurchaseForecastService purchaseForecastService;
    private final PurchaseForecastRepository purchaseForecastRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех прогнозов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка прогнозов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<PurchaseForecastDto>> getAll() {
        return ResponseEntity.ok(purchaseForecastService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение прогноза по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = ""),
            @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 401, message = "")
    })
    public ResponseEntity<PurchaseForecastDto> getById(@ApiParam(name = "id", type = "Long",
    value = "Переданный в URL id, по которому необходимо найти прогноз")
            @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) purchaseForecastRepository, id );
        return ResponseEntity.ok(purchaseForecastService.getById(id));
    }

    public ResponseEntity<PurchaseForecastDto> create() {
        return null;
    }

    public ResponseEntity<PurchaseForecastDto> update() {
        return null;
    }

    public ResponseEntity<PurchaseForecastDto> deleteById() {
        return null;
    }
}
