package src.main.java.com.trade_accounting.controllers.rest.finance;

import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.entity.finance.Funnel;
import com.trade_accounting.repositories.finance.FunnelRepository;
import com.trade_accounting.services.interfaces.finance.FunnelService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Funnel Rest Controller", description = "CRUD  операции с воронкой")
@Api(tags = "Funnel Rest Controller")
@RequestMapping("/api/funnel")
@RequiredArgsConstructor
public class FunnelRestController {

    private final FunnelService funnelService;
    private final CheckEntityService checkEntityService;
    private final FunnelRepository funnelRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает воронку продаж")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение воронки продаж"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<FunnelDto>> getAll() {
        return ResponseEntity.ok(funnelService.getAll());
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение воронки продаж полученной по заданным параметрам")
    public ResponseEntity<List<FunnelDto>> searchByFilter(
            @And({
                    @Spec(path = "statusName", params = "statusName", spec = Like.class),
                    @Spec(path = "count", params = "count", spec = Equal.class),
                    @Spec(path = "time", params = "time", spec = LikeIgnoreCase.class ),
                    @Spec(path = "conversion", params = "conversion", spec = LikeIgnoreCase.class),
                    @Spec(path = "price", params = "price", spec = LikeIgnoreCase.class)
            }) Specification<Funnel> spec ) {
        List<FunnelDto> funnelDtoList = funnelService.search(spec);
        return ResponseEntity.ok(funnelDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение сущности по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<FunnelDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти сущность")
                                              @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) funnelRepository, id);
        return ResponseEntity.ok(funnelService.getById(id));
    }
}
