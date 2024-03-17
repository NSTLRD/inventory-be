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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private MarketRepository marketRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MarketRepository marketRepository) {
        this.productRepository = productRepository;
        this.marketRepository = marketRepository;
    }


    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        Set<Market> markets = new HashSet<>();
        if (productCreateDTO.getMarketIds() != null && !productCreateDTO.getMarketIds().isEmpty()) {
            markets.addAll(marketRepository.findAllById(productCreateDTO.getMarketIds()));
        }

        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setDescription(productCreateDTO.getDescription());
        product.setPrice(productCreateDTO.getPrice());
        product.setMarkets(markets);
        product = productRepository.save(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setMarkets(markets.stream().map(market -> {
            MarketDTO marketDTO = new MarketDTO();
            marketDTO.setId(market.getId());
            marketDTO.setName(market.getName());
            marketDTO.setLocation(market.getLocation());
            return marketDTO;
        }).collect(Collectors.toList()));

        return productDTO;
    }
}