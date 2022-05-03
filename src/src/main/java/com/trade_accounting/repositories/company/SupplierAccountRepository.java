package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.dto.company.SupplierAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAccountRepository extends JpaRepository<SupplierAccount,Long>,
                                                      JpaSpecificationExecutor<SupplierAccount> {
//
//    @Query("select new com.trade_accounting.models.dto.company.SupplierAccountDto (" +
//            "e.id," +
//            "e.date," +
//            "e.company.id," +
//            "e.warehouse.id," +
//            "e.contract.id," +
//            "e.contractor.id," +
//            "e.isSpend," +
//            "e.comment) from SupplierAccount  e")
    @Query("SELECT s FROM SupplierAccount s")
    List<SupplierAccount> getAll();

    //List<SupplierAccount> findByTypeOfInvoice(TypeOfInvoice typeOfInvoice); //раскоментировать, когда будут реализованы фильтры

//    @Query("select new com.trade_accounting.models.dto.company.SupplierAccountDto (" +
//            "e.id," +
//            "e.date," +
//            "e.company.id," +
//            "e.warehouse.id," +
//            "e.contract.id," +
//            "e.contractor.id," +
//            "e.isSpend," +
//            "e.comment) from SupplierAccount  e where  e.id = :id")
    @Query("SELECT s FROM SupplierAccount s WHERE s.id = :id")
    SupplierAccountDto getById(@Param("id") Long id);

//    @Query(
//            "select new com.trade_accounting.models.dto.company.SupplierAccountDto (" +
//                    "e.id," +
//                    "e.date," +
//                    "e.company.id," +
//                    "e.warehouse.id," +
//                    "e.contract.id," +
//                    "e.contractor.id," +
//                    "e.isSpend," +
//                    "e.comment) from SupplierAccount  e where  lower(e.comment) " +
//                    "                                   like lower(concat('%', :nameFilter,'%'))"
//    )
    @Query("SELECT s FROM SupplierAccount s WHERE lower(s.comment) " +
            "like lower(concat('%', :nameFilter, '%')) ")
    List<SupplierAccountDto> searchByNameFilter(@Param("nameFilter") String nameFilter);

    @Query("select s from SupplierAccount s where lower(concat(cast(s.id as java.lang.String), ' ', s.comment)) like lower(concat('%', :nameFilter, '%'))")
    List<SupplierAccount> searchByIdAndNameFilter(@Param("nameFilter") String nameFilter);

//    @Query("select new com.trade_accounting.models.dto.company.SupplierAccountDto (" +
//            "e.id," +
//            "e.date," +
//            "e.company.id," +
//            "e.warehouse.id," +
//            "e.contract.id," +
//            "e.contractor.id," +
//            "e.isSpend," +
//            "e.comment) from SupplierAccount  e where  e.id = :nameFilter "
//    )
    @Query("SELECT s FROM SupplierAccount s WHERE s.id = :nameFilter  ")
    List<SupplierAccountDto> searchById(@Param("nameFilter") Long nameFilter);

    List<SupplierAccount> findByTypeOfInvoice(TypeOfInvoice typeOfInvoice);

    @Query("SELECT s FROM SupplierAccount s where lower(concat(s.id, s.comment, s.company.name, s.warehouse.name)) " +
            "like concat('%', :search, '%') and s.typeOfInvoice = :typeOfInvoice")
    List<SupplierAccountDto> findBySearchAndTypeOfInvoice(@Param("search") String search,
                                                  @Param("typeOfInvoice") TypeOfInvoice typeOfInvoice);
}
