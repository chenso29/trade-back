package src.main.java.com.trade_accounting.repositories.warehouse;


import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.dto.warehouse.ProductPriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("SELECT new com.trade_accounting.models.dto.warehouse.ProductPriceDto(price.id, price.typeOfPrice.id, price.value) " +
            "from Product product inner join product.productPrices as price where product.id = :id")
    List<ProductPriceDto> getPricesDtoByProductId(Long id);
}
