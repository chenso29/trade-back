package src.main.java.com.trade_accounting.controllers.rest.finance;


import com.trade_accounting.models.dto.finance.ReturnAmountByProductDto;
import com.trade_accounting.services.interfaces.finance.ReturnAmountByProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "ReturnAmountByProductMapper Rest Controller")
@Tag(name = "ReturnAmountByProductMapper Rest Controller", description = "CRUD операции с возвратом информации о возвратах по продуктам")
@RequiredArgsConstructor
@RequestMapping("/api/return-amount-by-product")
public class ReturnAmountByProductController {
    private final ReturnAmountByProductService returnAmountByProductService;

    @GetMapping
    @ApiOperation(value = "getTotalAmountByProductId", notes = "Получение списка всех возвратов по продукту")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение возвратов по продукту"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ReturnAmountByProductDto> getTotalReturnAmountByProduct(@ApiParam(
            name = "productId", value = "Id продукта по котороуму будет вычисляться общая сумма возврата")
                                                                                  @RequestParam("productId") Long productId, @RequestParam("invoiceId") Long invoiceId) {
        return ResponseEntity.ok(returnAmountByProductService.findAmountByProductIdAndInvoiceId(productId, invoiceId));
    }
}
