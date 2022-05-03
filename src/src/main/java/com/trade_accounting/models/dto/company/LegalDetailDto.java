package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegalDetailDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private Long addressDtoId;

    private String commentToAddress;

    private String inn;

    private String kpp;

    private String okpo;

    private String ogrn;

    private String numberOfTheCertificate;

    private String dateOfTheCertificate;

    private Long typeOfContractorDtoId;
}
