package src.main.java.com.trade_accounting.models.entity.company;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "contract_date", columnDefinition = "date default current_date")
    private LocalDate contractDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @Column(name = "amount")
    @ColumnDefault("0")
    private BigDecimal amount;

    @Column(name = "archive", columnDefinition = "boolean default false")
    private Boolean archive;

    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    private LegalDetail legalDetail;
}
