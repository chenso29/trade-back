package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long>, JpaSpecificationExecutor<Contract> {

    @Query("SELECT c FROM Contract c")
    List<Contract> getAll();

    @Query("SELECT c FROM Contract c WHERE c.id = :id")
    Contract getById(@Param("id") Long id);

    @Query(
            "from Contract c LEFT OUTER JOIN BankAccount AS cg " +
            "ON c.bankAccount.id =  cg.id " +
            "LEFT OUTER JOIN Contractor as top " +
            "ON c.contractor.id =  top.id " +
            "LEFT OUTER JOIN Company as address " +
            "ON c.company.id =  address.id " +
            "LEFT OUTER JOIN LegalDetail as ld " +
            "ON c.legalDetail.id =  ld.id " +
            " where lower(c.contractor.name) like lower(concat('%', :searchContr, '%'))"
    )
    List<Contract> search(@Param("searchContr") String searchContr);
}
