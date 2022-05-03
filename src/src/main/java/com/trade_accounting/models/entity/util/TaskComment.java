package src.main.java.com.trade_accounting.models.entity.util;

import com.trade_accounting.models.entity.client.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_comment")
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Type(type = "text")
    @Column(name = "comment")
    private String commentContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee publisher;

    @NotNull
    @Column(name = "published_date_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime publishedDateTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
}
