package src.main.java.com.trade_accounting.repositories.util;

import com.trade_accounting.models.entity.util.BonusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@Repository
public interface BonusProgramRepository extends JpaRepository<BonusProgram, Long> {

}
