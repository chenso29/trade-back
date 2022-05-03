package src.main.java.com.trade_accounting.models.dto.finance;

import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunnelDto {

    private Long id;

    private String statusName;

    private Long count;

    private String time;

    private String conversion;

    private String price;

    private String type;


    //Конструктор для listContractorsDataView
    public FunnelDto(Long id, String statusName, Long count, String conversion, String type) {
        this.id = id;
        this.statusName = statusName;
        this.count = count;
        this.conversion = conversion;
        this.type = type;
    }

}
