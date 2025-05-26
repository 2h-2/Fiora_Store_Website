package com.website.ecom_project.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.CheckOutDto;
import com.website.ecom_project.model.dto.OrderResponseDto;
import com.website.ecom_project.model.dto.OrderResponseUser;
import com.website.ecom_project.model.entity.Cart;
import com.website.ecom_project.model.entity.CartItem;
import com.website.ecom_project.model.entity.Order;
import com.website.ecom_project.model.entity.OrderItem;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.repository.CartRepository;
import com.website.ecom_project.repository.OrderItemRepository;
import com.website.ecom_project.repository.OrderRepository;
import com.website.ecom_project.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final UserRepository userRepo;
    private final CartRepository cartRepo;
    private final CartService cartService;


    @Transactional
    public void checkOut(CheckOutDto dto) {
        User user = userRepo.findById(dto.getUserId())
        .orElseThrow(() -> {
            System.out.println("User with ID " + dto.getUserId() + " not found!");
            return new RuntimeException("User not found!");
        });

        Cart cart = cartRepo.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        Order newOrder = new Order();

        newOrder.setUser(user);
        newOrder.setAddress(dto.getAddress());
        newOrder.setPhone(dto.getPhone());

        for (CartItem item : cart.getItems()) {
            BigDecimal itemTotal = item.getProductVariation().getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setItemPrice(item.getProductPrice());
            orderItem.setProductVariation(item.getProductVariation());
            orderItem.setQuantity(item.getQuantity());

            newOrder.getItems().add(orderItem);
        }

        newOrder.setTotalPrice(totalPrice);

        orderRepo.save(newOrder);

        cartService.clearCart(user.getId());
}

    public List<OrderResponseUser> getOrdersByUser(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found0"));

        List<Order> orders = orderRepo.findByUser(user);
        
        return orders.stream().map(order -> {
            OrderResponseUser response = new OrderResponseUser();

            response.setOrderId(order.getId());
            response.setOrderDate(order.getCreatedAt());
            response.setTotalPrice(order.getTotalPrice());

            return response;
        }).collect(Collectors.toList());
    }

    public List<OrderResponseDto> getAllOrders(){
        List<Order> orders = orderRepo.findAll();
        
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found");
        }


        return orders.stream().map(order ->{
            OrderResponseDto res = new OrderResponseDto();
            
            res.setOrderId(order.getId());
            res.setUserName(order.getUser().getUserName());
            res.setUserEmail(order.getUser().getEmail());
            res.setStatus(order.getStatus().name());
            res.setUserAddress(order.getAddress());
            res.setPhone(order.getPhone());
            res.setOrderDate(order.getCreatedAt());
            res.setTotalPrice(order.getTotalPrice());
            return res;

        }).collect(Collectors.toList());
    }

    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }
}
