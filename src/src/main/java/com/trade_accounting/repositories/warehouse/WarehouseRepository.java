package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {

    @Query("select new com.trade_accounting.models.dto.warehouse.WarehouseDto(e.id, e.name, e.sortNumber, e.address, e.commentToAddress, e.comment) from Warehouse e")
    List<WarehouseDto> getAll();

    @Query("select new com.trade_accounting.models.dto.warehouse.WarehouseDto(e.id, e.name, e.sortNumber, e.address, e.commentToAddress, e.comment) from Warehouse e where e.id = :id")
    WarehouseDto getById(@Param("id") Long id);

    Warehouse getWarehouseById(Long id);

    @Query("from Warehouse w " +
            "where lower ( concat(w.id, ' ', w.name, ' ', w.sortNumber, ' ', w.address, ' ', w.commentToAddress, ' ', w.comment)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<Warehouse> getBySearch(@Param("symbols") String search);
}
