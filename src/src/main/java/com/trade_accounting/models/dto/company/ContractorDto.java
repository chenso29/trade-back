package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDto {
    private Long id;

    private String name;

    private String shortname;

    private String sortNumber;

    private String phone;

    private String fax;

    private String email;

    private Long addressId;

    private String commentToAddress;

    private String comment;

    private String discountCardNumber;

    private List<Long> contactIds;

    private Long contractorGroupId;

    private Long typeOfPriceId;

    private List<Long> bankAccountIds;

    private Long legalDetailId;

    private Long contractorStatusId;

    private Long accessParametersId;
}
