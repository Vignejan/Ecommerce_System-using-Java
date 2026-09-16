package com.example.Ecommerce_Site.config;
import com.example.Ecommerce_Site.Entity.Product;
import com.example.Ecommerce_Site.DTO.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getSku(), product.getDescription(),
                product.getPrice(),product.getStock(), product.getBrand(),
                product.getSeller()!=null?product.getSeller().getId():null,
                product.getCompatibleVehicles().stream().map(V->V.getId()).collect(Collectors.toSet()),
                product.getOffers().stream().map(O->O.getId()).collect(Collectors.toSet())
        );
        return productDTO;
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setBrand(productDTO.getBrand());

        // ⚠️ Relationships NOT set here directly
        // seller, vehicles, offers should be set in Service layer

        return product;
    }



}
