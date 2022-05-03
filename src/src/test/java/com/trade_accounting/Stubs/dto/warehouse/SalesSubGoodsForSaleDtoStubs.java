package src.test.java.com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.model.warehouse.SalesSubGoodsForSaleModelStubs;
import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.utils.mapper.warehouse.SalesSubGoodsForSaleMapper;
import org.mapstruct.factory.Mappers;

public class SalesSubGoodsForSaleDtoStubs {
    private static final SalesSubGoodsForSaleMapper mapper = Mappers.getMapper(SalesSubGoodsForSaleMapper.class);

    public static SalesSubGoodsForSaleDto getDto(Long id) {
        return mapper.toDto(SalesSubGoodsForSaleModelStubs.getSalesSubGoodsForSale(id));
    }
}

