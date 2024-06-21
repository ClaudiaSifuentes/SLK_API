package com.spectralink.API_SLK.model.controller;
import com.spectralink.API_SLK.model.dto.ProductResponseDTO;
import com.spectralink.API_SLK.model.dto.StaffResponseDTO;
import com.spectralink.API_SLK.model.dto.UpdateRequestDTO;
import com.spectralink.API_SLK.model.entities.Product;
import com.spectralink.API_SLK.model.repository.ProductRepository;
import com.spectralink.API_SLK.model.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductoStock(@PathVariable Long id, @RequestBody UpdateRequestDTO requestDTO) {
        Optional<Product> updatedProduct = productService.updateProducto(id, requestDTO);
        return updatedProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //este metodo es para que reduzca el stock sin devoler toda la info
//    @PatchMapping("/{id}")
//    public ResponseEntity<Void> updateProductoStocktwo(@PathVariable Long id, @RequestBody UpdateRequestDTO requestDTO) {
//        productService.updateProductoStocktwo(id, requestDTO);
//        return ResponseEntity.noContent().build();
//    }






}
