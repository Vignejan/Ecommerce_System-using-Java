package com.example.Ecommerce_Site.Service.Interface_Service;

import com.example.Ecommerce_Site.DTO.ProductDTO;

import java.util.List;


public interface ProductServiceInt {
     List<ProductDTO> getProducts();
     ProductDTO updateProduct(ProductDTO newProduct,Long id);
     void deleteProduct();
     void deleteProductById(Long id);
//    public Optional<ProductDTO> getProductsByName(String name);
     ProductDTO getProductsById(Long id);
     void addProduct(ProductDTO product);

     static int add(ProductDTO product){
         return 10;
     }


}
