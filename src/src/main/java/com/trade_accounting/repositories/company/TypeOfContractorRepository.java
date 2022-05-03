package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.TypeOfContractor;
import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeOfContractorRepository extends JpaRepository<TypeOfContractor, Long> {

    Optional<TypeOfContractor> findByName(String name);


    @Query("select new com.trade_accounting.models.dto.company.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t ")
    List<TypeOfContractorDto> getAll();

    @Query("select new com.trade_accounting.models.dto.company.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t " +
            "where t.id=:id")
    TypeOfContractorDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.company.TypeOfContractorDto(" +
            "t.legalDetail.typeOfContractor.id, " +
            "t.legalDetail.typeOfContractor.name, " +
            "t.legalDetail.typeOfContractor.sortNumber" +
            ") " +
            "from Contractor t " +
            "where t.id=:id")
    TypeOfContractorDto getTypeOfContractorByContractorId(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.company.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t " +
            "where t.name=:name")
    TypeOfContractorDto getByName(String name);


}
