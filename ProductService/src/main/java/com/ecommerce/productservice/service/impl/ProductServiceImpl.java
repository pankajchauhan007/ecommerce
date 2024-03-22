package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.payload.ProductRequest;
import com.ecommerce.productservice.payload.ProductResponce;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponce> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::fromProduct) .collect(Collectors.toList());
    }


    @Override
    public Optional<ProductResponce> getProductById(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map( this :: fromProduct );
    }

    @Override
    public ProductResponce createProduct(ProductRequest productRequest) {
        Product product =Product.builder()
                .productId(productRequest.getProductId())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);
        return this.fromProduct(savedProduct);
    }

    @Override
    public ProductResponce UpdateProduct(int productId, ProductRequest updateProductRequest) {

        Optional<Product> existingProductOptional = productRepository.findById(productId);
              if(existingProductOptional.isPresent()){
                  Product existingProduct =existingProductOptional.get();
                  existingProduct.setProductName(updateProductRequest.getProductName());
                  existingProduct.setDescription(updateProductRequest.getDescription());
                  existingProduct.setPrice(updateProductRequest.getPrice());

                  Product updateProduct = productRepository.save(existingProduct);
                  return this.fromProduct(updateProduct);
              }
              else {
                  throw new RuntimeException("Product not found with ID:"+ productId);
              }

    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);

    }

    private ProductResponce fromProduct(Product product) {
        return ProductResponce.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
