package src.main.java.com.trade_accounting.models.entity.finance;


import com.trade_accounting.models.entity.util.ExpenseItem;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "payments")
public class Payment extends OperationsAbstract {

    @Column(name = "type_of_payment")
    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @Column(name = "type_of_document")
    private String typeOfDocument;

    @Column(name = "payment_methods")
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethods;

    @Column(name = "number")
    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @Column(name = "expense_item")
    @Enumerated(EnumType.STRING)
    private ExpenseItem expenseItem;

    @Column(name = "time")
    private LocalDateTime time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "is_conducted")
    private Boolean isConducted;

}
