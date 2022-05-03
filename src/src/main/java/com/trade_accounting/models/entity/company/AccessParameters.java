package src.main.java.com.trade_accounting.models.entity.company;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "access_parameters")
public class AccessParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "general_access")
    private Boolean generalAccess;

    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    private Department department;
}
