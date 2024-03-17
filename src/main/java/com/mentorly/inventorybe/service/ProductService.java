/**
 * @author Starling Diaz on 3/16/2024.
 * @Academy StarAcademy
 * @version inventory-be 1.0
 * @since 3/16/2024.
 */
package com.mentorly.inventorybe.service;

import com.mentorly.inventorybe.dto.ProductCreateDTO;
import com.mentorly.inventorybe.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductCreateDTO productCreateDTO);

    List<ProductDTO> listAllProduct();
    ProductDTO getProductById(Long productId);
    ProductDTO updateProduct(Long productId, ProductCreateDTO productCreateDTO);
    void deleteProduct(Long productId);
}
