package com.oms.controller;

import com.oms.dto.CustomerRequest;
import com.oms.dto.OrderResponse;
import com.oms.entity.Country;
import com.oms.entity.Customer;
import com.oms.entity.Order;
import com.oms.service.CountryService;
import com.oms.service.CustomerService;
import com.oms.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;
    private CountryService countryService;
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @Autowired
    public OrderController(OrderService orderService,
                           CustomerService customerService,
                           CountryService countryService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.countryService = countryService;
    }

    @GetMapping("/order-form")
    public String orderForm(HttpServletRequest request){
        List<Country> countries = countryService.findAll();
        request.setAttribute("countries", countries);
        return "order-form";
    }

    @PostMapping("/add-order")
    public String addOrder(HttpServletRequest request){
        if (request.getParameter("add") != null) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String addressLine1 = request.getParameter("addressLine1");
            //debugging log
            logger.info("AddressLine1 from request: " + addressLine1);
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String region = request.getParameter("region");
            String postalCode = request.getParameter("postalCode");
            String countryId_raw = request.getParameter("countryId");
            Long countryId = Long.parseLong(countryId_raw);
            CustomerRequest customerRequest = new CustomerRequest(firstName, lastName, email, phoneNumber, addressLine1, addressLine2, city, region, postalCode, countryId);
            logger.info("CustomerRequest Address Line I: " + customerRequest.getAddressLine1());
            Customer customer = customerService.saveCustomer(customerRequest);
            logger.info("Customer Address Line I: " + customer.getAddressLine1());
            Order order = orderService.saveOrder(customer);
            OrderResponse orderResponse = orderService.mapToOrderResponse(order);
            logger.info("Order id aa: " + order.getOrderId());
            return "redirect:/order-form";
        } else if (request.getParameter("cancel") != null){
            return "redirect:/order-form";
        }
        return "order-form";
    }

    @GetMapping("/view-orders")
    public String viewOrders(HttpServletRequest request){
        List<OrderResponse> orders = orderService.fetchAllOrder();
        request.setAttribute("orders", orders);
        return "view-orders";
    }

    @GetMapping("/order-details")
    public String orderDetails(HttpServletRequest request){
        String orderId_raw = request.getParameter("orderId");
        Long orderId = Long.parseLong(orderId_raw.replaceAll("\\D+", ""));
        OrderResponse order = orderService.fetchOrderById(orderId);
        request.setAttribute("order", order);
        return "order-details";
    }

}
