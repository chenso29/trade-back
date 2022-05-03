package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long>, JpaSpecificationExecutor<Contractor> {

    @Query(
            "from Contractor c LEFT OUTER JOIN ContractorGroup AS cg " +
                    "ON c.contractorGroup.id =  cg.id " +
                    "LEFT OUTER JOIN TypeOfPrice as top " +
                    "ON c.typeOfPrice.id =  top.id " +
                    "LEFT OUTER JOIN Address as address " +
                    "ON c.address.id =  address.id " +
                    "LEFT OUTER JOIN LegalDetail as ld " +
                    "ON c.legalDetail.id =  ld.id " +
                    " where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
                    "      or c.phone like concat('%',  :searchTerm, '%')" +
                    "      or c.comment like concat('%', :searchTerm, '%')"
    )
    List<Contractor> search(@Param("searchTerm") String searchTerm);

    Contractor getContractorById(Long id);
}
