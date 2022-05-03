package src.main.java.com.trade_accounting.models.entity.invoice;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices_received")
@Builder
public class InvoiceReceived {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data")
    private LocalDateTime data;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Acceptance acceptance;

    @NotNull
    @Column(name = "incom_number")
    private Long incomNumber;

    @NotNull
    @Column(name = "incom_data")
    private LocalDateTime incomData;

    @Column(name = "is_send")
    @ColumnDefault("false")
    private Boolean isSend;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean isPrint;

    @Column(name = "is_Spend")
    @ColumnDefault("false")
    private Boolean isSpend;

    @Column(name = "comment")
    private String comment;

}
