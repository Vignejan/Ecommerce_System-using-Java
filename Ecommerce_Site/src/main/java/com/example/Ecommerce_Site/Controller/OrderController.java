package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.DTO.RequestDTO.OrderRequestDTO;
import com.example.Ecommerce_Site.Entity.Order;
import com.example.Ecommerce_Site.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrders(){
       return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    //Place the order
    @PostMapping("/place/{userId}")
    public String placeOrder(@RequestBody OrderRequestDTO order, @PathVariable Long userId) {
        Order savedOrder = orderService.placeOrder(order, userId);
        return "Order Placed Successfully: " + savedOrder.getId();
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody Order order){
        orderService.updateOrder(id,order);
        return "Order updated successfully";
    }

    //upadate status
    @PutMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status){
         orderService.updateOrderStatus(id,status);
         return "Order updated successfully";
    }
    @DeleteMapping("/clear")
    public String deleteOrders(){
        orderService.deleteOrders();
        return "Orders deleted successfully";
    }

    @DeleteMapping("{id}")
    public String deleteOrderBYId(@PathVariable Long id){
        orderService.deleteOrderBYId(id);
        return "Order deleted successfully";
    }

    @PutMapping("{id}/cancel")
    public String cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
        return "Order cancelled successfully";
    }

    // ✅ Get orders of a user
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
}
