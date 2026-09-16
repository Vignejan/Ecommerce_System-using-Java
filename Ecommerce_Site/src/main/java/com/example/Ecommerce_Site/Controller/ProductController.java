package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.DTO.ProductDTO;
import com.example.Ecommerce_Site.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
     public List<ProductDTO> getProducts(){
        return productService.getProducts();
     }

//     @GetMapping("/{id}")
//     public Product getProductsById(Long id){
//        return productService.getProductsById(id);
//     }
//
//     @GetMapping("/name/{name}")
//     public List<Product> getProductsByName(@PathVariable String name){
//       return productService.getProductsByName(name);
//     }
//
//
//     @PostMapping
//    public String addProduct(@RequestBody Product product){
//        productService.addProduct(product);
//        return "Product added successfully at this id :"+product.getId();
//     }
//
//     @PutMapping("/{id}")
//    public String updateProduct(@RequestBody Product product,@PathVariable Long id){
//        productService.updateProduct(product,id);
//        return "Product updated successfully at this id :"+product.getId();
//     }
//
//     @DeleteMapping("/clear")
//    public String deleteProduct(@RequestBody Product product){
//        productService.deleteProduct();
//        return "All Product deleted successfully";
//     }
//
//     @DeleteMapping("/{id}")
//    public String deleteProductById(@PathVariable Long id){
//        productService.deleteProductById(id);
//        return "Product deleted successfully at this id :"+id;
//    }
}
