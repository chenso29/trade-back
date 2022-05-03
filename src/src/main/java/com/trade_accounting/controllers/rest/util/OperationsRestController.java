package src.main.java.com.trade_accounting.controllers.rest.util;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.dto.util.OperationsDto;
import com.trade_accounting.repositories.util.OperationsRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.util.OperationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Tag(name = "Operations Rest Controller", description = "Операции со списком документов")
@Api(tags = "Operations Rest Controller")
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationsRestController {
    private final OperationsService operationService;
    private final CheckEntityService checkEntityService;
    private final OperationsRepository operationsRepository;


    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех документов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка документов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<OperationsDto>> getAll() {
        return ResponseEntity.ok(operationService.getAll());
    }

    @GetMapping("/queryOperations")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка документов по заданным параметрам фильтра")
    public ResponseEntity<List<OperationsDto>> getByFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class), // work
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "company.name", params = "company", spec = Equal.class), // work
                    @Spec(path = "isSent", params = "sent", spec = Equal.class), // work
                    @Spec(path = "isPrint", params = "print", spec = Equal.class) // work
                    // typeOfOperation
                    // price
                    // @Spec(path = "warehouse.name", params = "from", spec = Equal.class),
                    // @Spec(path = "warehouseTo.name", params = "to", spec = Equal.class),
                    // @Spec(path = "contactor.name", params = "contactor", spec = Equal.class),
            }) Specification<OperationsAbstract> spec) {
        return ResponseEntity.ok(operationService.search(spec));
    }

    @GetMapping("/quickSearch")
    @ApiOperation(value = "search", notes = "Получение списка документов из строки быстрого поиска")
    public ResponseEntity<List<OperationsDto>> quickSearch(@RequestParam("search") String search) {
        return ResponseEntity.ok(operationService.quickSearch(search));
    }

    @GetMapping("/quickSearchRecycle")
    @ApiOperation(value = "search", notes = "Получение списка документов в корзине из строки быстрого поиска")
    public ResponseEntity<List<OperationsDto>> quickSearchRecycle(@RequestParam("searchDeleted") String search) {
        return ResponseEntity.ok(operationService.quickSearchRecycle(search));
    }
}
