package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternalOrderRepository extends JpaRepository<InternalOrder, Long>, JpaSpecificationExecutor<InternalOrder> {
    @Query("SELECT i FROM InternalOrder i")
    List<InternalOrder> getAll();

    @Query("SELECT i FROM InternalOrder i WHERE i.id = :id")
    InternalOrder getById(@Param("id") Long id);

    /*В случае необходимости поиска по компании, складу и комментарию разкоммитить*/
    //    @Query(
//            "from InternalOrder i LEFT OUTER JOIN Company as c on i.company.id = c.id" +
//                    " LEFT outer join Warehouse as w on i.warehouse.id = w.id " +
//                    "where lower(i.comment) like lower (concat('%', :searchItem, '%')) " +
//                    "or lower(w.name) like lower(concat('%', :searchItem,'%'))" +
//                    "or lower(c.name) like lower(concat('%', :searchItem,'%'))"
//
//
//    )

    @Query("from InternalOrder a" +
            " where lower(concat(a.id, ' ', a.comment))" +
            " like lower(concat('%', :searchItem, '%'))")
    List<InternalOrder> search(@Param("searchItem") String searchItem);
}
