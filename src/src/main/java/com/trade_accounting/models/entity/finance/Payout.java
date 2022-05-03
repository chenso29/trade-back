package src.main.java.com.trade_accounting.models.entity.finance;


import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.company.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "payouts")
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    RetailStore retailStore;

    @Column(name = "who_was_paid")
    String whoWasPaid;

    @OneToOne(fetch = FetchType.LAZY)
    Company company;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    Boolean isSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    Boolean isPrint = false;

    @Column(name = "comment")
    String comment;
}
