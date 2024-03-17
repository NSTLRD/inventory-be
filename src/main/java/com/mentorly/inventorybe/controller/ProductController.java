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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Manejo de productos")
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
    @Operation(summary = "Crear un producto", description = "Crea un producto")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        ProductDTO productDTO = productService.createProduct(productCreateDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos disponibles")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> products = productService.listAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por su id", description = "Obtiene un producto por su id")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto por su id", description = "Actualiza un producto por su id")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO productCreateDTO) {
        ProductDTO productDTO = productService.updateProduct(id, productCreateDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto por su id", description = "Elimina un producto por su id")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
