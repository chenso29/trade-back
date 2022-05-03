package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessParametersRepository extends JpaRepository<AccessParameters, Long> {
    AccessParameters getAccessParametersById(Long id);
}
