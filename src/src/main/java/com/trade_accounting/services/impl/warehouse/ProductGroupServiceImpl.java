package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.repositories.warehouse.ProductGroupRepository;
import com.trade_accounting.services.interfaces.warehouse.ProductGroupService;
import com.trade_accounting.utils.mapper.warehouse.ProductGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductGroupServiceImpl implements ProductGroupService {

    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;

    @Override
    public List<ProductGroupDto> getAll() {
        return productGroupRepository.findAll().stream()
                .map(productGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductGroupDto getById(Long id) {
        return productGroupMapper.toDto(
                productGroupRepository.findById(id).orElse(new ProductGroup())
        );
    }

    @Override
    public ProductGroupDto create(ProductGroupDto dto) {
        ProductGroup productGroup = productGroupMapper.toModel(dto);

        if (dto.getId() != null) {
            productGroup.setProductGroup(
                    productGroupRepository.findById(dto.getParentId()).orElse(null)
            );
        } else {
            productGroup.setProductGroup(null);
        }
        productGroupRepository.save(productGroup);

        return dto;
    }


    @Override
    public ProductGroupDto update(ProductGroupDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        productGroupRepository.deleteById(id);
    }

}
