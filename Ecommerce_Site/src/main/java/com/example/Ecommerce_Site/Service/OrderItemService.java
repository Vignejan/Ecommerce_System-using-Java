package com.example.Ecommerce_Site.Service;

import com.example.Ecommerce_Site.Entity.Order;
import com.example.Ecommerce_Site.Entity.OrderItem;
import com.example.Ecommerce_Site.Model.Product;
import com.example.Ecommerce_Site.Repository.OrderItemReo;
import com.example.Ecommerce_Site.Repository.OrderRepo;
import com.example.Ecommerce_Site.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderItemService {

    @Autowired
    OrderItemReo orderItemReo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;


    public OrderItem addItemToOrder(Long orderId, String productName, int quantity) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->
                new RuntimeException("order not found at id " + orderId));

        Product product=productRepo.findByName(productName).
                orElseThrow(()-> new RuntimeException("product not found at name " + productName));

        //Check Stock
        if(product.getStock()<quantity){
            throw new RuntimeException("product not enough stock for order " + orderId);
        }

//     Reduce stock
        product.setStock(product.getStock()-quantity);
        productRepo.save(product);

        // create order item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

        // save orderItem
        OrderItem savedItem = orderItemReo.save(orderItem);

        // recalc total order price
        order.setTotalPrice(order.getTotalPrice().add(orderItem.getPrice()));
        orderRepo.save(order);

        return savedItem;
    }

    public void removeItemFromOrder(Long orderId, String productName) {
        Product product=productRepo.findByName(productName).
                orElseThrow(()-> new RuntimeException("product not found at name " + productName));

        Order order=orderRepo.findById(orderId).
                orElseThrow(()->new RuntimeException("order not found at id " + orderId));

        // Find the order item inside this order
        OrderItem orderItem=order.getItems().stream().filter(i->i.getProduct().getName().equals(productName))
                .findFirst().orElseThrow(()->new RuntimeException("product not found at name " + productName));


        // Restore product stock
        product.setStock(product.getStock() + orderItem.getQuantity());
        productRepo.save(product);

        order.getItems().remove(orderItem);

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()) {
            total = total.add(item.getPrice());
        }
        order.setTotalPrice(total);

        orderRepo.save(order);
    }
}

