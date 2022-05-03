package src.main.java.com.trade_accounting.repositories.fias;

import com.trade_accounting.models.entity.fias.FiasAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressDbRepository extends JpaRepository<FiasAddressModel, Long> {
    @Query("from FiasAddressModel as address where address.aolevel like :level")
    List<FiasAddressModel> findAllByLevel(@Param("level") String level);

    @Query("from FiasAddressModel as address where address.parentguid like :aoguid")
    List<FiasAddressModel> findAdressesByAoguid(@Param("aoguid") String aoguid);
}
