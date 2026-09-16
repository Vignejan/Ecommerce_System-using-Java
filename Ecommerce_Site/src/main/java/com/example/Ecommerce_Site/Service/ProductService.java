package com.example.Ecommerce_Site.Service;


import com.example.Ecommerce_Site.Entity.Product;
import com.example.Ecommerce_Site.DTO.ProductDTO;
import com.example.Ecommerce_Site.Repository.ProductRepo;
import com.example.Ecommerce_Site.Service.Interface_Service.ProductServiceInt;
import com.example.Ecommerce_Site.config.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProductService implements ProductServiceInt {
        @Autowired
        ProductMapper productMapper;
        @Autowired
        ProductRepo productRepo;


    public ProductDTO getProductsById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found with id:" + id));
        return productMapper.productToProductDTO(product);
    }

    public void addProduct(ProductDTO product) {
        productRepo.save(productMapper.productDTOToProduct(product));
    }

    @Override
    public List<ProductDTO> getProducts() {
        if(productRepo.findAll().isEmpty()){
            throw new RuntimeException("it's empty product.Atleast add one product ");
        }
        return productRepo.findAll()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(ProductDTO newProduct, Long id) {

        Product existingProduct = productRepo.findById(id).orElseThrow(() ->
                new RuntimeException("product not found" + id + " and cannot be updated"));
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setSku(newProduct.getSku());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setStock(newProduct.getStock());
        existingProduct.setBrand(newProduct.getBrand());

        Product savedProduct = productRepo.save(existingProduct);
        return productMapper.productToProductDTO(savedProduct);

    }

    public void deleteProduct() {
        productRepo.deleteAll();
    }

    public void deleteProductById(Long id) {

        if(!productRepo.existsById(id)){
            throw new RuntimeException("product not found at this id:" +id+ " and cannot be deleted");
        }
        productRepo.deleteById(id);
    }
    public Optional<ProductDTO> getProductsByName(String name) {
        Optional<Product> product = productRepo.findByName(name);
        if (product.isEmpty()) {
            throw new RuntimeException("product not found with name" +name);
        }
        return product.map(productMapper::productToProductDTO);
    }
}
