package src.main.java.com.trade_accounting.models.entity.production;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;


/**
 * Класс-модель Тех.процесса (вкладка производство)
 * @author German Kireev
 * @version 1.0
 * @since October 2021
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "technical_processes")
public class TechnicalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * имя техпроцесса
     */
    @NotNull
    private String name;

    /**
     * описание техпроцесса
     */
    private String description;

    /**
     * этапы производства, что относятся к техпроцессу
     * OneToMany - маппинг происходит через Hibernate, но вся логика создания и связывания таблиц в sql миграциях
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TECHNICAL_PROCESS_PRODUCTION_STAGES",
            joinColumns={@JoinColumn(name="TECHNICAL_PROCESS_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUCTION_STAGE_ID", referencedColumnName="ID")})
    private Set<StagesProduction> stagesProductionSet;

    /**
     * техпроцесс в архиве, по умолчанию "false", то есть действующий
     */
    @ColumnDefault("false")
    private Boolean isArchived;

    /**
     * доступ к техпроцессу, по умолчанию "В общем доступе", то есть "true"
     */
    @ColumnDefault("true")
    private Boolean isShared;

    /**
     * владелец-отдел данного техпроцесса
     */
    @OneToOne
    @NotNull
//    @JoinColumn(name = "department_owner_id")
    private Department departmentOwner;

    /**
     * владелец-сотрудник данного техпроцесса
     */
    @OneToOne
    @NotNull
    private Employee employeeOwner;

    /**
     * дата последнего изменения техпроцесса
     */
    @NotNull
    @Column(name = "dateOfChange")
    private LocalDateTime dateOfChanged;

    /**
     * сотрудник, внёсший изменения в техпроцесс последним
     */
    @OneToOne
    @NotNull
    private Employee employeeWhoLastChanged;

}
