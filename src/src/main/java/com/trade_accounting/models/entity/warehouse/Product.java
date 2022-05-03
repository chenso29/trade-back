package src.main.java.com.trade_accounting.models.entity.warehouse;


import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight", scale = 3)
    private BigDecimal weight;

    @Column(name = "volume", scale = 6)
    private BigDecimal volume;

    @Column(name = "purchase_price", scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    @Column(name = "archive")
    private Boolean archive = false;

    @Column(name = "service")
    private Boolean service = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductPrice> productPrices;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaxSystem taxSystem;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGroup productGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private AttributeOfCalculationObject attributeOfCalculationObject;

    //Страна происхождения
    @Column(name = "Country")
    private String countryOrigin;

    //Артикул(код товара присваеваемый производителем)
    @Column(name = "itemNubmber")
    private int itemNumber;

    //НДС
    @Column(name = "saleTax")
    private String saleTax;

    //Неснижаемый остаток
    @Column(name = "minimumBalance")
    private int minimumBalance;

    public Product(Long id,
                   String name,
                   BigDecimal weight,
                   BigDecimal volume,
                   BigDecimal purchasePrice,
                   String description,
                   Unit unit,
                   Boolean archive,
                   Boolean service,
                   Contractor contractor,
                   List<ProductPrice> productPrices,
                   TaxSystem taxSystem,
                   List<Image> images,
                   ProductGroup productGroup,
                   AttributeOfCalculationObject attributeOfCalculationObject,
                   String countryOrigin,
                   int itemNumber,
                   String saleTax,
                   int minimumBalance) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.service = service;
        this.contractor = contractor;
        this.productPrices = productPrices;
        this.taxSystem = taxSystem;
        this.images = images;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;
        this.countryOrigin = countryOrigin;
        this.itemNumber = itemNumber;
        this.saleTax = saleTax;
        this.minimumBalance = minimumBalance;
    }

    public Product(String name, BigDecimal weight, BigDecimal volume, BigDecimal purchasePrice, String description, Unit unit, Boolean archive, Contractor contractor, List<ProductPrice> productPrices, TaxSystem taxSystem, List<Image> images, List<File> files, ProductGroup productGroup, AttributeOfCalculationObject attributeOfCalculationObject) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.contractor = contractor;
        this.productPrices = productPrices;
        this.taxSystem = taxSystem;
        this.images = images;
        this.files = files;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;
    }

    public Product(String name,
                   BigDecimal purchasePrice,
                   String description,
                   BigDecimal weight,
                   BigDecimal volume,
                   Boolean archive,
                   Unit unit,
                   ProductGroup productGroup,
                   TaxSystem taxSystem,
                   Contractor contractor,
                   AttributeOfCalculationObject attributeOfCalculationObject,
                   List<Image> images,
                   List<ProductPrice> productPrices) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.contractor = contractor;
        this.taxSystem = taxSystem;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;
        this.images = images;
        this.productPrices = productPrices;
    }

    public Product(Long id,
                   String name,
                   BigDecimal weight,
                   BigDecimal volume,
                   BigDecimal purchasePrice,
                   String description,
                   Boolean archive) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
    }

    public Product(String name,
                   BigDecimal purchasePrice,
                   String description,
                   Unit unit,
                   Boolean archive,
                   Boolean service,
                   List<ProductPrice> productPrices,
                   TaxSystem taxSystem,
                   List<Image> images,
                   ProductGroup productGroup,
                   AttributeOfCalculationObject attributeOfCalculationObject) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.unit = unit;
        this.archive = archive;
        this.service = service;
        this.productPrices = productPrices;
        this.taxSystem = taxSystem;
        this.images = images;
        this.productGroup = productGroup;
        this.attributeOfCalculationObject = attributeOfCalculationObject;
    }

    public Product(Long id,
                   String name,
                   BigDecimal purchasePrice,
                   String description,
                   Boolean archive,
                   Boolean service) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.archive = archive;
        this.service = service;
    }


}
