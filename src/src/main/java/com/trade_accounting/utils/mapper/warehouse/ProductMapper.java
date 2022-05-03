package src.main.java.com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.AttributeOfCalculationObject;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.entity.units.Unit;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product toModel(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id(productDto.getId());
        product.archive(productDto.getArchive());
        product.countryOrigin(productDto.getCountryOrigin());
        product.description(productDto.getDescription());
        product.itemNumber(productDto.getItemNumber());
        product.minimumBalance(productDto.getMinimumBalance());
        product.name(productDto.getName());
        product.purchasePrice(productDto.getPurchasePrice());
        product.saleTax(productDto.getSaleTax());
        product.service(productDto.getService());
        product.volume(productDto.getVolume());
        product.weight(productDto.getWeight());
        product.attributeOfCalculationObject(productDtoToAttributeOfCalculationObject(productDto));
        product.contractor(productDtoToContractor(productDto));
        product.productGroup(productDtoToProductGroup(productDto));
        product.taxSystem(productDtoToTaxSystem(productDto));
        product.unit(productDtoToUnit(productDto));
        product.productPrices(productDtoToProductPrice(productDto));


        return product.build();
    }


    default ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (product == null) {
            return null;
        } else {
            productDto.setId(product.getId());
            productDto.setArchive(product.getArchive());
            productDto.setCountryOrigin(product.getCountryOrigin());
            productDto.setDescription(product.getDescription());
            productDto.setItemNumber(product.getItemNumber());
            productDto.setMinimumBalance(product.getMinimumBalance());
            productDto.setName(product.getName());
            productDto.setPurchasePrice(product.getPurchasePrice());
            productDto.setSaleTax(product.getSaleTax());
            productDto.setService(product.getService());
            productDto.setVolume(product.getVolume());
            productDto.setWeight(product.getWeight());
            productDto.setProductPriceIds(product.getProductPrices().stream().map(ProductPrice::getId).collect(Collectors.toList()));
            if (product.getUnit()==null){
                return null;
            } else {
                productDto.setUnitId(product.getUnit().getId());
                if (product.getContractor()==null){
                    return null;
                } else {
                    productDto.setContractorId(product.getContractor().getId());
                    if (product.getProductGroup()==null){
                        return null;
                    } else {
                        productDto.setProductGroupId(product.getProductGroup().getId());
                        if (product.getAttributeOfCalculationObject()==null) {
                            return null;
                        }else {
                            productDto.setAttributeOfCalculationObjectId(product.getAttributeOfCalculationObject().getId());
                            if (product.getTaxSystem()==null){
                                return null;
                            }else {
                                productDto.setTaxSystemId(product.getTaxSystem().getId());
                                return productDto;
                            }
                        }
                    }
                }
            }
        }
    }

    default Contractor productDtoToContractor(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Contractor contractor = new Contractor();
        contractor.setId(productDto.getContractorId());
        return contractor;
    }

    default AttributeOfCalculationObject productDtoToAttributeOfCalculationObject(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        AttributeOfCalculationObject attribute = new AttributeOfCalculationObject();
        attribute.setId(productDto.getAttributeOfCalculationObjectId());
        return attribute;
    }

    default ProductGroup productDtoToProductGroup(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(productDto.getProductGroupId());
        return productGroup;
    }

    default TaxSystem productDtoToTaxSystem(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        TaxSystem taxSystem = new TaxSystem();
        taxSystem.setId(productDto.getTaxSystemId());
        return taxSystem;
    }

    default Unit productDtoToUnit(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Unit unit = new Unit();
        unit.setId(productDto.getUnitId());
        return unit;
    }

    default List<ProductPrice> productDtoToProductPrice(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        List<ProductPrice> list = new ArrayList<>();
        for (Long l : productDto.getProductPriceIds()){
            ProductPrice productPrice = new ProductPrice();
            productPrice.setId(l);

            list.add(productPrice);
        }

        return list;
    }

    List<ProductDto> toListDto(Collection<Product> products);

}