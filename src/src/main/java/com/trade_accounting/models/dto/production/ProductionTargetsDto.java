package src.main.java.com.trade_accounting.models.dto.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionTargetsDto { //Dto для "Производственные задания"

    private Long id; //поле "№"

    private String date; //поле "время"

    private Long companyId; //поле "ID организации"

    private String deliveryPlannedMoment; // поле "Планируемая дата производства"

    private Long materialWarehouse; //Заглушка для поля "Склад для материалов"

    private Long productionWarehouse; // Заглушка для поля "Склад для продукции"

    private String productionStart; // поле "начало производства"

    private String productionEnd; // поле "завершение производства"

    private Boolean shared = false; // поле "общий доступ"

    private String Owner; // Заглушка для поля "Владелец-отдел"

    private String employeeOwner; // Заглушка для поля "Владелец-сотрудник"

    private Boolean published = false; // поле "отправлено"

    private Boolean printed = false; // поле "напечатано"

    private String description; // поле "комментарий"

    private String updated; // поле "когда изменен"

    private String updatedByName; // Заглушка для поля "Кто изменил"

}
