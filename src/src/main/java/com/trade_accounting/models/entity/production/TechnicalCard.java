package src.main.java.com.trade_accounting.models.entity.production;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "technical_cards")
public class TechnicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "production_cost")
    private String productionCost;

    @ManyToOne(fetch = FetchType.LAZY)
    private TechnicalCardGroup technicalCardGroup;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TechnicalCardProduction> finalProduction;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TechnicalCardProduction> materials;
}
