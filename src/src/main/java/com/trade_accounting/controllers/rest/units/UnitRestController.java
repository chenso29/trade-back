package src.main.java.com.trade_accounting.controllers.rest.units;

import com.trade_accounting.models.dto.warehouse.MovementDto;
import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.units.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Unit Rest Controller", description = "CRUD операции с единицами измерения")
@Api(tags = "Unit Rest Controller")
@RequestMapping("/api/unit")
@RequiredArgsConstructor
public class UnitRestController {

    private final UnitService unitService;
    private final CheckEntityService checkEntityService;
    private final UnitRepository unitRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех единиц измерения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех единиц измерения"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<UnitDto>> getAll() {
        List<UnitDto> units = unitService.getAll();
        return ResponseEntity.ok(units);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает единицу измерения по её Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Единица измерения найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )

//    public ResponseEntity<UnitDto> getById(@ApiParam(
//            name = "id",
//            type = "Long",
//            value = "ID переданный в URL по которому необходимо найти единицу измерения") @PathVariable Long id) {
//        checkEntityService.checkExists((JpaRepository) unitRepository, id);
//        return ResponseEntity.ok(unitService.getById(id));
//    }
    public ResponseEntity<UnitDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) unitRepository, id);
        return ResponseEntity.ok(unitRepository.getById(id));
    }

    @ApiOperation(value = "create", notes = "Регистрация новой единицы измерения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Единица измерения успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping
    public ResponseEntity<UnitDto> create(@ApiParam(
            name = "unitDto",
            value = "DTO единицы измерения, которую необходимо создать") @RequestBody UnitDto unitDto) {
        return ResponseEntity.ok().body(unitService.create(unitDto));
    }

    @ApiOperation(value = "update", notes = "Обновление информации о единицы измерения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о единицы измерения успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<?> update(@ApiParam(
            name = "unitDto",
            value = "DTO единицы измерения, которую необходимо обновить") @RequestBody UnitDto unitDto) {
        return ResponseEntity.ok().body(unitService.update(unitDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление единицы измерения по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Единица измерения удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить единицу измерения") @PathVariable Long id) {
        unitService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка единиц измерения по фильтру")
    public ResponseEntity<List<UnitDto>> getAll(
            @And({
                    @Spec(path = "unitType", params = "unitType", spec = LikeIgnoreCase.class),
                    @Spec(path = "shortName", params = "shortName", spec = LikeIgnoreCase.class),
                    @Spec(path = "fullName", params = "fullName", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = Equal.class),
                    @Spec(path = "generalAccess", params = "generalAccess", spec = Equal.class),
                    @Spec(path = "departmentOwner", params = "departmentOwner", spec = LikeIgnoreCase.class),
                    @Spec(path = "employeeOwner", params = "employeeOwner", spec = LikeIgnoreCase.class),
                    @Spec(path = "dateOfChange", params = "dateOfChange", spec = GreaterThanOrEqual.class),
                    @Spec(path = "employeeChange", params = "employeeChange", spec = LikeIgnoreCase.class)
            }) Specification<Unit> specification) {

        return ResponseEntity.ok(unitService.search(specification));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка единиц измерения по заданным параметрам")
    public ResponseEntity<List<UnitDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(unitService.searchByString(search));
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину единицы измерения по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Единица измерения перенесена в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить счет")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) unitRepository, id);
        unitService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }
}
