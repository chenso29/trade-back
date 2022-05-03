package src.main.java.com.trade_accounting.models.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCommentDto {

    private Long id;

    private String commentContent;

    @NotNull
    private Long publisherId;

    @NotNull
    private String publishedDateTime;

    private Long taskId;

    public TaskCommentDto(Long id
            , String commentContent
            , Long publisherId
            , LocalDateTime publishedDateTime
            , Long taskId) {
        this.id = id;
        this.commentContent = commentContent;
        this.publisherId = publisherId;
        this.publishedDateTime = publishedDateTime.toString();
        this.taskId = taskId;
    }
}
