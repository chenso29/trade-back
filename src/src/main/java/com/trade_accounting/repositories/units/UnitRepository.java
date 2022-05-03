package src.main.java.com.trade_accounting.repositories.units;

import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.units.UnitDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long>, JpaSpecificationExecutor<Unit> {

    @Query("select new com.trade_accounting.models.dto.units.UnitDto(e.id, e.shortName, e.fullName, e.sortNumber) from Unit e")
    List<UnitDto> getAll();

    @Query("select new com.trade_accounting.models.dto.units.UnitDto(e.id, e.shortName, e.fullName, e.sortNumber) from Unit e where e.id = :id")
    UnitDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.units.UnitDto(p.unit.id, p.unit.shortName, p.unit.fullName, p.unit.sortNumber) from Product p where p.id = :id")
    UnitDto getUnitByProductId(@Param("id") Long id);

    @Query("from Unit u " +
            "where lower ( concat(u.id, ' ', u.shortName, ' ', u.fullName, ' ', u.sortNumber)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Unit> getBySearch(@Param("symbols") String search);
}

