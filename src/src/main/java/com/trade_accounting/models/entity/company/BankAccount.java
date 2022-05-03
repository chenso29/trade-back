package src.main.java.com.trade_accounting.models.entity.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rcbic")
    private String rcbic;

    @Column(name = "bank")
    private String bank;

    @Column(name = "address")
    private String address;

    @Column(name = "correspondent_account")
    private String correspondentAccount;

    @Column(name = "account")
    private String account;

    @Column(name = "main_account")
    private Boolean mainAccount;

    @Column(name = "sort_number")
    private String sortNumber;


}

