package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/{orderId}")
    public String addItemToOrder(@PathVariable Long orderId,@RequestParam String productName,@RequestParam int quantity){
        orderItemService.addItemToOrder(orderId,productName,quantity);
        return "OrderItem "+ productName+" added successfully";
    }

    @DeleteMapping("/{orderId}")
    public String removeItemFromOrder(@PathVariable Long orderId,@RequestParam String productName){
        orderItemService.removeItemFromOrder(orderId,productName);
        return "OrderItem "+ productName+" removed successfully";
    }

}
