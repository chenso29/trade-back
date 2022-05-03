package src.main.java.com.trade_accounting.controllers.rest.finance;


import com.trade_accounting.models.dto.finance.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.finance.MoneySubProfitLossDto;
import com.trade_accounting.services.interfaces.finance.MoneySubProfitLossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@Tag(name = "MoneySubProfitLoss Rest Controller", description = "Получение данных о прибыли и убытках")
@Api(tags = "MoneySubProfitLoss Rest Controller")
@RequestMapping("/api/profitloss")
@RequiredArgsConstructor
public class MoneySubProfitLossRestController {

    private final MoneySubProfitLossService moneySubProfitLossService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение данных о прибыли и убытках")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение данных о прибыли и убытках"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MoneySubProfitLossDto> getMoneySubProfitLossDto() {
        return ResponseEntity.ok(moneySubProfitLossService.getMoneySubProfitLossDto());
    }

    @GetMapping("/filter")
    @ApiOperation(value = "filter", notes = "Фильтр транзакций")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Транзакция отфильтрована"),
            @ApiResponse(code = 201, message = "Запрос принят и отфильтрован"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MoneySubProfitLossDto> filter(
            @ApiParam(name = "startDatePeriod", type = "LocalDate", value = "Ввести начало периода для фильтра")
            @RequestParam(name = "startDatePeriod", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDatePeriod,
            @ApiParam(name = "endDatePeriod", type = "LocalDate", value = "Ввести окончание периода для фильтра")
            @RequestParam(name = "endDatePeriod", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDatePeriod,
            @ApiParam(name = "companyId", type = "Long", value = "Ввести id компании")
            @RequestParam(name = "companyId", required = false) Long companyId) {
        return ResponseEntity.ok(moneySubProfitLossService.filter(startDatePeriod, endDatePeriod, companyId));
    }
}
