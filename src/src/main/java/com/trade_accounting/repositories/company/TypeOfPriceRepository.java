package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.TypeOfPrice;
import com.trade_accounting.models.dto.company.TypeOfPriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeOfPriceRepository extends JpaRepository<TypeOfPrice, Long> {

    Optional<TypeOfPrice> findByName(String name);

    TypeOfPrice getTypeOfPriceById(Long id);

    @Query("select new com.trade_accounting.models.dto.company.TypeOfPriceDto(t.id, t.name, t.sortNumber) from TypeOfPrice t")
    List<TypeOfPriceDto> getAll();

    @Query("select new com.trade_accounting.models.dto.company.TypeOfPriceDto(t.id, " +
            "t.name, " +
            "t.sortNumber) " +
            "from TypeOfPrice t" +
            " where t.id = :id")
    TypeOfPriceDto getById(@Param("id") Long id);


    @Query("select new com.trade_accounting.models.dto.company.TypeOfPriceDto(" +
            "t.typeOfPrice.id, " +
            "t.typeOfPrice.name, " +
            "t.typeOfPrice.sortNumber) " +
            "from Contractor t " +
            "where t.id = :id")
    TypeOfPriceDto getTypeOfPriceByContractorId(@Param("id") Long id);
}
