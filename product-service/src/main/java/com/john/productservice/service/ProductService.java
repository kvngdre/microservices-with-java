package com.john.productservice.service;

import com.john.productservice.dto.ProductRequest;
import com.john.productservice.dto.ProductResponse;
import com.john.productservice.model.Product;
import com.john.productservice.repositroy.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j // this is for logging like winston I guess.
//@RequiredArgsConstructor // this can be used in place of the constructor for the required arg productRepository.
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product newProduct = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        productRepository.save(newProduct);

        log.info("Product {} has been created successfully.", newProduct.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> foundProducts = productRepository.findAll();

        return foundProducts.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
