package src.main.java.com.trade_accounting.models.entity.retail;

import com.trade_accounting.models.entity.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "retail_makings")
public class RetailMaking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private RetailStore retailStore;

    @Column(name = "from_whom")
    String fromWhom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "is_print")
    private Boolean isPrint;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "comment")
    private String comment;
}
