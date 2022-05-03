package src.main.java.com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceProductMapper {
    //InvoiceProduct
    @Mappings({
            @Mapping(source = "invoice.id", target = "invoiceId"),
            @Mapping(source = "product.id", target = "productId")
    })
    InvoiceProductDto toDto(InvoiceProduct invoiceProduct);

    @Mappings({
            @Mapping(source = "invoiceId", target = "invoice.id"),
            @Mapping(source = "productId", target = "product.id")
    })
    InvoiceProduct toModel(InvoiceProductDto invoiceProductDto);
}
