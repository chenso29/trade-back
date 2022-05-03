package src.main.java.com.trade_accounting.services.interfaces.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface SalesSubGoodsForSaleService extends AbstractService<SalesSubGoodsForSaleDto>,
        SearchableService<SalesSubGoodsForSale, SalesSubGoodsForSaleDto> {

    List<SalesSubGoodsForSaleDto> searchByFilter(String search);
}
