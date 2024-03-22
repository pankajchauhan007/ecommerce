package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.payload.ProductRequest;
import com.ecommerce.productservice.payload.ProductResponce;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<ProductResponce> getAllProducts();
    public Optional<ProductResponce> getProductById(int productId);
    public ProductResponce createProduct(ProductRequest productRequest);
    public ProductResponce UpdateProduct(int productId, ProductRequest updateProductRequest );
    public void deleteProduct(int productId);

}
