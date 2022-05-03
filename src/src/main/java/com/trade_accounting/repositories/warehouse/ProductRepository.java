package src.main.java.com.trade_accounting.repositories.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("from Product p where p.productGroup.id = :id and p.service = false")
    List<Product> getAllByProductGroupId(@Param("id") Long id);

    @Query("from Product p where p.productGroup.id = :id and p.service = true")
    List<Product> getAllServicesByProductGroupId(@Param("id") Long id);

    @Query("from Product p where p.contractor.id = :id and p.service = false")
    List<Product> getAllByContractorId(@Param("id") Long id);

    @Query("from Product p where p.contractor.id = :id and p.service = true")
    List<Product> getAllServicesByContractorId(@Param("id") Long id);

    @Query("from Product p where concat(p.id, ' ', p.name, ' ', p.description) like concat('%', :query, '%') and p.service = false")
    List<Product> search(@Param("query") String query);

    @Query("from Product p where concat(p.id, ' ', p.name, ' ', p.description) like concat('%', :query, '%') and p.service = false")
    List<Product> searchService(@Param("query") String query);
}