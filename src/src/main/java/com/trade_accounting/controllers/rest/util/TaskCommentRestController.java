package src.main.java.com.trade_accounting.controllers.rest.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.repositories.util.TaskCommentRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.util.TaskCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/task_comments")
public class TaskCommentRestController {

    private final TaskCommentService commentService;
    private final CheckEntityService checkEntityService;
    private final TaskCommentRepository commentRepository;

    @ApiOperation(value = "getAll", notes = "Получение списка всех комментариев")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех комментариев"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<TaskCommentDto>> getAll() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @ApiOperation(value = "search", notes = "Получение списка комментариев по заданному фильтру")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех комментариев с учётом фильтра"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping(value = "search")
    public ResponseEntity<List<TaskCommentDto>> search(
            @And({
                    @Spec(path = "id", params = "comment_id", spec = Equal.class),
                    @Spec(path = "commentContent", params = "comment", spec = LikeIgnoreCase.class),
                    @Spec(path = "publisher.id", params = "publisher_id", spec = Equal.class),
                    @Spec(path = "task.id", params = "task_id", spec = Equal.class),
                    @Spec(path = "creationDateTime", params = {"lower_date", "upper_date"}, spec = Between.class),
            }) Specification<TaskComment> spec) {
        return ResponseEntity.ok(commentService.search(spec));
    }

    @ApiOperation(value = "getById", notes = "Получение комментария по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение комментария"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskCommentDto> getById(@PathVariable("id") long id) {
        checkEntityService.checkExists((JpaRepository) commentRepository, id);
        return ResponseEntity.ok(commentService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создание нового комментария")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Комментарий создан"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<TaskCommentDto> create(@RequestBody TaskCommentDto dto) {
        var created = commentService.create(dto);

        URI commentURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .normalize()
                .toUri();

        return ResponseEntity.ok().body(created);
    }

    @ApiOperation(value = "update", notes = "Обновление комментария")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Комментарий обновлен"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<TaskCommentDto> update(@RequestBody TaskCommentDto dto) {
        return ResponseEntity.ok().body(commentService.update(dto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление комментария по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Комментарий удален"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        commentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
