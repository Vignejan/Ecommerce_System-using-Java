package com.example.Ecommerce_Site.Service;

import com.example.Ecommerce_Site.DTO.RequestDTO.OrderItemRequestDTO;
import com.example.Ecommerce_Site.DTO.RequestDTO.OrderRequestDTO;
import com.example.Ecommerce_Site.Entity.Order;
import com.example.Ecommerce_Site.Entity.Product;
import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.DTO.OrderDTO;
import com.example.Ecommerce_Site.Entity.OrderItem;
import com.example.Ecommerce_Site.Entity.User;
import com.example.Ecommerce_Site.Model.OrderStatus;
import com.example.Ecommerce_Site.Repository.*;
import com.example.Ecommerce_Site.config.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.el.lang.ELArithmetic.add;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private Order orderEntity;
    @Autowired
    private OrderItemReo orderItemReo;


    public List<OrderDTO> getOrders() {
        if(orderRepo.findAll().isEmpty()){
            throw new RuntimeException("Now it's empty order.");
        }
        return orderRepo.findAll().stream().map(orderMapper::ordertoOrderDTO).
                collect(Collectors.toList());
    }
    public OrderDTO getOrderById(Long id) {
        orderEntity=orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order with id " + id + " not found."));
        return orderMapper.ordertoOrderDTO(orderEntity);
    }
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        orderEntity=orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order with id " + id + " not found."));
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setStatus(OrderStatus.valueOf(orderDTO.getOrderStatus()));
        orderEntity.setTotalPrice(orderDTO.getTotalPrice());
        addressRepo.findById(orderDTO.getAddressId()).orElseThrow(() -> new RuntimeException("Address with id " + id + " not found."));
        orderEntity.setShippingAddress(addressRepo.findById(orderDTO.getAddressId()).orElseThrow(()->new RuntimeException("Address with id " + id + " not found.")));
        Set<OrderItem> orderItems=orderDTO.getProductIds().
                stream().map(
                        productId->{
                            Product prod=productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product with id " + productId + " not found."));
                            OrderItem orderItem=new OrderItem();
                            orderItem.setProduct(prod);
                            orderItem.setOrder(orderEntity);

                            return orderItem;
                        }
                ).collect(Collectors.toSet());
        orderEntity.setItems(orderItems);
        return orderMapper.ordertoOrderDTO(orderEntity);

    }
    public void deleteOrderBYId(Long id) {
        if(orderRepo.findById(id).isEmpty()){
            throw new RuntimeException("Order with id " + id + " not found and Can't be deleted.");
        }
    }
    public void deleteOrders() {
        if(orderRepo.findAll().isEmpty()){
            throw new RuntimeException("Orders deleted successfully.");
        }
        orderRepo.deleteAll();
    }
    public OrderDTO placeOrder(OrderRequestDTO orderReqDTO, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Address address = addressRepo.findById(orderReqDTO.getAddressId()).orElseThrow(() ->
                new RuntimeException("Address not found with id " + orderReqDTO.getAddressId()));

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setShippingAddress(address);
        order.setStatus(OrderStatus.valueOf("PENDING"));

            Set<OrderItem> orderItemSet=new HashSet<>();
        BigDecimal totalprice=BigDecimal.ZERO;
        //Proccess Item
        for (OrderItemRequestDTO items : orderReqDTO.getOrderItems()) {
            Product dbProduct = productRepo.findById(items.getProductId()).
                    orElseThrow(() -> new RuntimeException("Product with id " + items.getProductId() + " not found."));


            //Check stock
            if (dbProduct.getStock() < items.getQuantity()) {
                throw new RuntimeException("This " + dbProduct.getName() + " is out of Stock");
            }
            dbProduct.setStock(dbProduct.getStock() - items.getQuantity()); // reduce stock
            productRepo.save(dbProduct);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(dbProduct);
            orderItem.setQuantity(items.getQuantity());
            orderItem.setPrice(dbProduct.getPrice());
            BigDecimal subtotal=dbProduct.getPrice().multiply(new BigDecimal(items.getQuantity()));
            orderItem.setSubTotalPrice(subtotal);

                  totalprice= totalprice.add(subtotal);
            orderItemSet.add(orderItem);

        }
         order.setItems(orderItemSet);
        order.setTotalPrice(totalprice);
         return orderMapper.ordertoOrderDTO(orderRepo.save(order));
    }
    public void updateOrderStatus(Long id, String status) {
        if(orderRepo.findById(id).isEmpty()){
            throw new RuntimeException("Order with id " + id + " not found.");
        }
        Order order = getOrderById(id);
        order.setStatus(status);
        orderRepo.save(order);
    }

    public void cancelOrder(Long id) {
        if(orderRepo.findById(id).isEmpty()){
            throw new RuntimeException("Order with id " + id + " not found.");
        }
        Order order = getOrderById(id);
        if(order.getStatus().equalsIgnoreCase("Delivered")){
            throw new RuntimeException("Order with id " + id + " not found and delivered order can't be cancelled.");
        }

        // restore stock
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepo.save(product);
        }

        order.setStatus("Cancelled");
        orderRepo.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}
