/**
 * @author Starling Diaz on 3/16/2024.
 * @Academy StarAcademy
 * @version inventory-be 1.0
 * @since 3/16/2024.
 */

package com.mentorly.inventorybe.controller;

import com.mentorly.inventorybe.dto.ProductCreateDTO;
import com.mentorly.inventorybe.dto.ProductDTO;
import com.mentorly.inventorybe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * @param productCreateDTO
     * @return ProductDTO
     * @since 3/16/2024
     * @version inventory-be 1.0
     * This method creates a product
    **/
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        ProductDTO productDTO = productService.createProduct(productCreateDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    /**
     * @return List of all products
     * @since 3/16/2024
     * @version inventory-be 1.0
     * This method returns a list of all products
    **/
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> products = productService.listAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * @param id
     * @return ProductDTO
     * @since 3/16/2024
     * @version inventory-be 1.0
     * This method returns a product by its id
     **/
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    /**
     * @param id
     * @param productCreateDTO
     * @return ProductDTO
     * @since 3/16/2024
     * @version inventory-be 1.0
     * This method updates a product by its id
     **/
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO productCreateDTO) {
        ProductDTO productDTO = productService.updateProduct(id, productCreateDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    /**
     * @param id
     * @since 3/16/2024
     * @version inventory-be 1.0
     * This method deletes a product by its id
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
