package com.oms.service;

import com.oms.dto.CustomerResponse;
import com.oms.dto.OrderResponse;
import com.oms.entity.Customer;
import com.oms.entity.Order;
import com.oms.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;
    private CustomerService customerService;

    @Autowired
    public OrderService(IOrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }
    @Transactional
    public Order saveOrder(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (customer.getOrder() != null) {
            throw new IllegalArgumentException("Customer already has an order");
        }
        Order order = new Order(LocalDateTime.now(), customer);
        Order savedOrder = orderRepository.save(order);
        savedOrder.setOrderCode(String.format("ORD%03d", savedOrder.getOrderId()));
        return orderRepository.save(savedOrder);
    }
    public OrderResponse mapToOrderResponse(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        OrderResponse orderResponse = new OrderResponse(
            order.getOrderCode(),
            order.getOrderDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
            customerService.mapToResponse(order.getCustomer())
        );
        return orderResponse;
    }

    public List<OrderResponse> fetchAllOrder() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            orderResponses.add(mapToOrderResponse(order));
        }
        return orderResponses;
    }

    public OrderResponse fetchOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return mapToOrderResponse(order);
    }
}
