/**
 * @author Starling Diaz on 3/16/2024.
 * @Academy StarAcademy
 * @version inventory-be 1.0
 * @since 3/16/2024.
 */
package com.mentorly.inventorybe.service;

import com.mentorly.inventorybe.dto.ProductCreateDTO;
import com.mentorly.inventorybe.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductCreateDTO productCreateDTO);
}
