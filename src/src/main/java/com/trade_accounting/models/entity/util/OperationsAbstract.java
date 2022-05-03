package src.main.java.com.trade_accounting.models.entity.util;


import com.trade_accounting.models.entity.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "operations")
public abstract class OperationsAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    private Boolean isSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean isPrint = false;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_recyclebin")
    @ColumnDefault("false")
    private Boolean isRecyclebin = false;
}
