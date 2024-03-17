/**
 * @author Starling Diaz on 3/16/2024.
 * @Academy StarAcademy
 * @version inventory-be 1.0
 * @since 3/16/2024.
 */

package com.mentorly.inventorybe.service.impl;

import com.mentorly.inventorybe.dto.MarketDTO;
import com.mentorly.inventorybe.dto.ProductCreateDTO;
import com.mentorly.inventorybe.dto.ProductDTO;
import com.mentorly.inventorybe.model.Market;
import com.mentorly.inventorybe.model.Product;
import com.mentorly.inventorybe.repository.MarketRepository;
import com.mentorly.inventorybe.repository.ProductRepository;
import com.mentorly.inventorybe.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MarketRepository marketRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.marketRepository = marketRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        Set<Market> markets = new HashSet<>();
        if (productCreateDTO.getMarketIds() != null && !productCreateDTO.getMarketIds().isEmpty()) {
            markets.addAll(marketRepository.findAllById(productCreateDTO.getMarketIds()));
        }
        Product product = modelMapper.map(productCreateDTO, Product.class);
        product.setMarkets(markets);
        product = productRepository.save(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> listAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductCreateDTO productCreateDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        modelMapper.map(productCreateDTO, product);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        productRepository.delete(product);
        log.info("Product with id: {} has been deleted", productId);
    }

}