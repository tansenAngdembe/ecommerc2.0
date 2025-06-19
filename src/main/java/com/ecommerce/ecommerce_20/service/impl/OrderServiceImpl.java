package com.ecommerce.ecommerce_20.service.impl;


import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.OrderRequest;
import com.ecommerce.ecommerce_20.dto.Response.product.OrderResponse;
import com.ecommerce.ecommerce_20.entity.customer.*;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.enums.OrderStatus;
import com.ecommerce.ecommerce_20.enums.PaymentMethod;
import com.ecommerce.ecommerce_20.enums.PaymentStatus;
import com.ecommerce.ecommerce_20.repository.*;
import com.ecommerce.ecommerce_20.service.OrderService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {


    private final CustomersRepo customersRepo;
    private final CartRepo cartRepo;
    private final OrdersRepo ordersRepo;
    private final AddressRepo addressRepo;
    private final CartItemsRepo cartItemsRepo;

    private final PaymentServiceImpl paymentServiceImpl;

    public OrderServiceImpl(CustomersRepo customersRepo, CartRepo cartRepo, CartItemsRepo cartItemsRepo, OrdersRepo ordersRepo, AddressRepo addressRepo,PaymentServiceImpl paymentServiceImpl) {
        this.customersRepo = customersRepo;
        this.cartRepo = cartRepo;
        this.ordersRepo = ordersRepo;
        this.addressRepo = addressRepo;
        this.cartItemsRepo = cartItemsRepo;
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @Override
    @Transactional
    public ApiResponse<?> placeOrder(OrderRequest orderRequest, Principal principal) {
        try {
            Customers customer = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Optional<Cart> cartCustomer = cartRepo.findByCustomers(customer);
            if (cartCustomer.isEmpty()) {
                return ResponseUtil.getSuccessMessage(-1, "Customer not found");
            }

            List<CartItems> cartItems = cartItemsRepo.findByCartAndIsDeletedFalse(cartCustomer.get());

            List<CartItems> selectedItems = cartItems
                    .stream()
                    .filter(itm -> orderRequest.getProductIds().contains(itm.getProduct().getUniqueId())).toList();

            if (selectedItems.isEmpty()) {
                return ResponseUtil.getSuccessMessage(-1, "Product not found with provided id");
            }

            Address shippingAddress = addressRepo.findById(orderRequest.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found"));
            Orders order = new Orders();
            order.setCustomer(customer);
            order.setOrderDate(Instant.now());
            order.setStatus(OrderStatus.PLACED);
            order.setPaymentStatus(PaymentStatus.PENDING);
            order.setShippingAddress(shippingAddress);
            List<OrdersItems> ordersItemsList = new ArrayList<>();
            double totalPrice = 0.0;

            for (CartItems cartItem : selectedItems) {
                OrdersItems ordersItem = new OrdersItems();
                ordersItem.setOrders(order);
                ordersItem.setQuantity(cartItem.getQuantity());
                ordersItem.setPrice(cartItem.getProduct().getPrice());
                ordersItem.setProducts(cartItem.getProduct());

                totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
                ordersItemsList.add(ordersItem);
            }

            order.setOrdersItems(ordersItemsList);
            order.setTotalPrice(totalPrice);

            Long orderid = ordersRepo.save(order).getId();
            OrderResponse newOrderResponse = new OrderResponse();
            newOrderResponse.setId(orderid);

            // removing cart items
            cartItems.stream().filter(cartItem -> orderRequest.getProductIds().contains(cartItem.getProduct().getUniqueId()))
                    .forEach(item -> {
                                item.setIsDeleted(true);
                                cartItemsRepo.save(item);
                            }
                    );



            return ResponseUtil.getSuccessMessage(-1, "Order successfully placed",newOrderResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
