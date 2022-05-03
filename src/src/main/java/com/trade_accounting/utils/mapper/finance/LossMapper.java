package src.main.java.com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LossMapper {

    default Loss toModel(LossDto lossDto) {

        if (lossDto == null) {
            return null;
        }

        List<LossProduct> lossProducts =
                lossDto.getLossProductsIds()
                        .stream()
                        .map(element -> LossProduct.builder().id(element).build())
                        .collect(Collectors.toList());

        return Loss.builder()
                .id(lossDto.getId())
                .date(LocalDateTime.parse(lossDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .warehouse(Warehouse.builder().id(lossDto.getWarehouseId()).build())
                .company(Company.builder().id(lossDto.getCompanyId()).build())
                .isSent(lossDto.getIsSent())
                .isPrint(lossDto.getIsPrint())
                .comment(lossDto.getComment())
                .lossProducts(lossProducts)
                .isRecyclebin(lossDto.getIsRecyclebin())
                .build();
    }

    default LossDto toDto(Loss loss) {
        LossDto lossDto = new LossDto();

        if (loss == null) {
            return null;
        } else {
            lossDto.setId(loss.getId());
            lossDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(loss.getDate()));
            lossDto.setIsSent(loss.getIsSent());
            lossDto.setIsPrint(loss.getIsPrint());
            lossDto.setComment(loss.getComment());
            lossDto.setIsRecyclebin(loss.getIsRecyclebin());
            lossDto.setLossProductsIds(
                    loss.getLossProducts().stream()
                            .map(LossProduct::getId)
                            .collect(Collectors.toList())
            );

            if (loss.getCompany() == null) {
                return null;
            } else {
                lossDto.setCompanyId(loss.getCompany().getId());

                if (loss.getWarehouse() == null) {
                    return null;
                } else {
                    lossDto.setWarehouseId(loss.getWarehouse().getId());
                    return lossDto;
                }
            }
        }
    }
}
