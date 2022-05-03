package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.ContractorStatus;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funnel")
public class Funnel {
    @Id
    private Long id;
    @Column
    private String statusName;
    @Column
    private Long count;
    @Column(name = "funnel_time")
    private String time;
    @Column
    private String conversion;
    @Column
    private String price;
    @Column(name = "type")
    private String type;
}
