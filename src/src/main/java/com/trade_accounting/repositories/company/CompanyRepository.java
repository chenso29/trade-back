package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    Company getCompanyByBankAccounts(BankAccount bankAccount);

    Company findCompanyByEmail(String email);

    Company getCompaniesById(Long id);
}
