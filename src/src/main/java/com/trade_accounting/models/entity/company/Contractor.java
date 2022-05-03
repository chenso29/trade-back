package src.main.java.com.trade_accounting.models.entity.company;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contractors")
@Builder
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "comment_to_address")
    private String commentToAddress;

    @Column(name = "comment")
    private String comment;

    @Column(name = "dicsount_card_number")
    private String discountCardNumber;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Contact> contact;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContractorGroup contractorGroup;

    @OneToOne(fetch = FetchType.LAZY)
    private TypeOfPrice typeOfPrice;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

    @OneToOne(fetch = FetchType.LAZY)
    private LegalDetail legalDetail;

    @OneToOne(fetch = FetchType.LAZY)
    private ContractorStatus contractorStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private AccessParameters accessParameters;
}
