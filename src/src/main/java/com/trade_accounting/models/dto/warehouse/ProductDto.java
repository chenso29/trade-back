package src.main.java.com.trade_accounting.models.dto.warehouse;


import com.trade_accounting.models.dto.util.FileDto;
import com.trade_accounting.models.dto.util.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal weight;

    private BigDecimal volume;

    private BigDecimal purchasePrice;

    private String description;

    private Long unitId;

    private Boolean archive = false;

    private Boolean service = false;

    private Long contractorId;

    private List<Long> productPriceIds;

    private Long taxSystemId;

    private List<ImageDto> imageDtos;

    private List<FileDto> fileDtos;

    private Long productGroupId;

    private Long attributeOfCalculationObjectId;

    private String countryOrigin;

    private int itemNumber;

    private String saleTax;

    private int minimumBalance;

    public ProductDto(String name,
                      BigDecimal weight,
                      BigDecimal volume,
                      BigDecimal purchasePrice,
                      String description,
                      Boolean archive) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
    }

    public ProductDto(Long id,
                      String name,
                      BigDecimal weight,
                      BigDecimal volume,
                      BigDecimal purchasePrice,
                      String description,
                      Boolean archive) {
        this(name, weight, volume, purchasePrice, description, archive);
        this.id = id;
    }

    public ProductDto(String name, BigDecimal purchasePrice, String description, Boolean archive, Boolean service) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
        this.service = service;
    }

    public ProductDto(Long id, String name, BigDecimal purchasePrice, String description, Boolean archive, Boolean service) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
        this.service = service;
    }

    public ProductDto(Long id, String name, BigDecimal weight,
                      BigDecimal volume, BigDecimal purchasePrice,
                      String description, Long unitId, Boolean archive,
                      Long contractorId, List<Long> productPriceIds,
                      Long taxSystemId, List<ImageDto> imageDtos,
                      Long productGroupId, Long attributeOfCalculationObjectId) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unitId = unitId;
        this.archive = archive;
        this.contractorId = contractorId;
        this.productPriceIds = productPriceIds;
        this.taxSystemId = taxSystemId;
        this.imageDtos = imageDtos;
        this.productGroupId = productGroupId;
        this.attributeOfCalculationObjectId = attributeOfCalculationObjectId;
    }

    public ProductDto(Long id, String name, BigDecimal weight,
                      BigDecimal volume, BigDecimal purchasePrice,
                      String description, Long unitId, Boolean archive,
                      Boolean service, Long contractorId,
                      List<Long> productPriceIds, Long taxSystemId,
                      List<ImageDto> imageDtos, Long productGroupId,
                      Long attributeOfCalculationObjectId, String countryOrigin,
                      int itemNumber, String saleTax, int minimumBalance) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unitId = unitId;
        this.archive = archive;
        this.service = service;
        this.contractorId = contractorId;
        this.productPriceIds = productPriceIds;
        this.taxSystemId = taxSystemId;
        this.imageDtos = imageDtos;
        this.productGroupId = productGroupId;
        this.attributeOfCalculationObjectId = attributeOfCalculationObjectId;
        this.countryOrigin = countryOrigin;
        this.itemNumber = itemNumber;
        this.saleTax = saleTax;
        this.minimumBalance = minimumBalance;
    }
}
