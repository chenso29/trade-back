package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.ContractorStatus;
import com.trade_accounting.models.dto.company.ContractorStatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorStatusRepository extends JpaRepository<ContractorStatus, Long> {

    @Query("select new com.trade_accounting.models.dto.company.ContractorStatusDto(" +
            "s.id, " +
            "s.name) from ContractorStatus s where s.id = :id")
    ContractorStatusDto getById(@Param("id") Long id);
}
