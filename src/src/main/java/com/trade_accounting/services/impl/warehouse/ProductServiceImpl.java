package src.main.java.com.trade_accounting.services.impl.warehouse;

import javax.validation.constraints.NotNull;

import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.warehouse.ProductPriceRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
import com.trade_accounting.utils.mapper.util.FileMapper;
import com.trade_accounting.utils.mapper.util.ImageMapper;
import com.trade_accounting.utils.mapper.warehouse.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final FileRepository fileRepository;
    private final ProductPriceRepository productPriceRepository;

    private final ImageMapper imageMapper;
    private final ProductMapper productMapper;
    private final FileMapper fileMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getOne(id);

        ProductDto productDto = productMapper.toDto(product);
        productDto.setImageDtos(imageMapper.toListDto(product.getImages()));
        productDto.setFileDtos(fileMapper.toListDto(product.getFiles()));

        return productDto;
    }

    @Override
    public ProductDto create(@NotNull ProductDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Product product = productMapper.toModel(dto);
        product.setImages(savedImages);
        savedFiles.forEach(file -> file.setProduct(product));
        product.setFiles(savedFiles);
        List<ProductPrice> prices = new ArrayList<>();
        product.getProductPrices()
                .forEach(productPrice -> prices.add(productPriceRepository.getOne(productPrice.getId())));
        product.setProductPrices(prices);
        productRepository.saveAndFlush(product);
        return dto;
    }


    @Override
    public ProductDto update(ProductDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "product");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Product product = productMapper.toModel(dto);
        product.setImages(savedImages);
        savedFiles.forEach(file -> file.setProduct(product));
        product.setFiles(savedFiles);
        List<ProductPrice> prices = new ArrayList<>();
        product.getProductPrices()
                .forEach(productPrice -> prices.add(productPriceRepository.getOne(productPrice.getId())));
        product.setProductPrices(prices);
        productRepository.saveAndFlush(product);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.getOne(id);
        productRepository.deleteById(id);
        product.getFiles().forEach(file -> {
            try {
                Files.deleteIfExists(Path.of(file.getPlacement() + file.getKey() + file.getExtension()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public List<ProductDto> search(Specification<Product> spec) {
        List<Product> productList = productRepository.findAll(spec);
        return productMapper.toListDto(productList);
    }

    @Override
    public PageDto<ProductDto> search(Specification<Product> specification, Pageable pageParam) {
        Page<Product> page = productRepository.findAll(specification, pageParam);
        return new PageDto<>(
                page.getContent().stream().map(productMapper::toDto).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumberOfElements()
        );
    }

    @Override
    public List<ProductDto> search(String value) {
        return productMapper.toListDto(productRepository.search(value));
    }
}
