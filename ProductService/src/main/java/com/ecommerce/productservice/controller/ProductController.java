package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.payload.ProductRequest;
import com.ecommerce.productservice.payload.ProductResponce;
import com.ecommerce.productservice.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseEntity<ProductResponce> createProduct (@RequestBody ProductRequest productRequest){
    ProductResponce createdProduct = productServiceImpl.createProduct(productRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductResponce>> getProductById(@PathVariable int productId){
    Optional<ProductResponce> product = Optional.ofNullable(productServiceImpl.getProductById(productId)
            .orElseThrow(() -> new RuntimeException("Product Not Found With Id " + productId)));
    return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponce>> getAllProducts(){
        List<ProductResponce> productList = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponce> updateProduct(@PathVariable int productId,
                                                         @RequestBody ProductRequest updateProductRequest){
        ProductResponce updateProduct = productServiceImpl.UpdateProduct(productId, updateProductRequest);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponce> deleteProduct(@PathVariable int productId){
        productServiceImpl.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
