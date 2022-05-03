package src.main.java.com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    List<Invoice> findByTypeOfInvoice(TypeOfInvoice typeOfInvoice);

    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e")
    List<InvoiceDto> getAll();


    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e where e.date >= :dateTime")
    List<InvoiceDto> getFromDateTime(LocalDateTime dateTime);


    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e where e.contractor.id = :id")
    List<InvoiceDto> findByContractorId(Long id);


    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e where lower(concat(e.id, e.comment, e.company.name, e.warehouse.name, e.invoicesStatus.statusName)) " +
            "like concat('%', :search, '%') and e.typeOfInvoice = :typeOfInvoice")
    List<InvoiceDto> findBySearchAndTypeOfInvoice(@Param("search") String search,
                                                  @Param("typeOfInvoice") TypeOfInvoice typeOfInvoice);

    @Query("select new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e where e.id = :id")
    InvoiceDto getById(@Param("id") Long id);

    @Query("SELECT new com.trade_accounting.models.dto.invoice.InvoiceDto(" +
            "e.id, " +
            "e.date, " +
            "e.typeOfInvoice, " +
            "e.company.id," +
            "e.contractor.id," +
            "e.warehouse.id," +
            "e.isSpend," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment," +
            "e.invoicesStatus.id) from Invoice e " +
            "where lower(concat(e.id, e.comment)) like lower(concat('%', :query,'%')) and e.typeOfInvoice = :typeOfInvoice")
    List<InvoiceDto> search(@Param("query") String query, @Param("typeOfInvoice") TypeOfInvoice typeOfInvoice);

}
