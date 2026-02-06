package com.oms.service;

import com.oms.dto.CustomerRequest;
import com.oms.dto.CustomerResponse;
import com.oms.dto.OrderResponse;
import com.oms.entity.Customer;
import com.oms.entity.Order;
import com.oms.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final IOrderRepository orderRepository;
    private CustomerService customerService;

    @Autowired
    public OrderService(IOrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }


    public Order saveOrder(CustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        Customer customer = customerService.findByEmail(request.getEmail())
                .orElseGet(() -> customerService.saveCustomer(request));

        //initialize new order to get id
        Order order = new Order(LocalDateTime.now(), customer);
        Order savedOrder = orderRepository.save(order);
        //after save one time, got new id, then can format new orderCode base on id and save to db
        String orderCode = String.format("ORD%03d", savedOrder.getOrderId());
        savedOrder.setOrderCode(orderCode);
        return orderRepository.save(savedOrder);
    }

    public OrderResponse mapToOrderResponse(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        OrderResponse orderResponse = new OrderResponse(
            order.getOrderCode(),
            //format date for view
            order.getOrderDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
            customerService.mapToResponse(order.getCustomer())
        );
        return orderResponse;
    }

    //func used for view all order responses
    public List<Order> fetchAllOrder() {
        return orderRepository.findAll();
    }

    //func used for view order response details
    public OrderResponse fetchOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return mapToOrderResponse(order);
    }
}
