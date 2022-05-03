package src.main.java.com.trade_accounting.repositories.finance;

import com.trade_accounting.models.entity.finance.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

    void deleteAllByContractId(@Param("id") Long id);

    @Query("from Payment e" +
            " where lower(concat(e.company.name, ' ', e.contractor.name, ' ', e.number))" +
            " like lower(concat('%', :req, '%'))")
    List<Payment> search(@Param("req") String request);


}
