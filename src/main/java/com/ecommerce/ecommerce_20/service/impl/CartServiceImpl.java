package com.ecommerce.ecommerce_20.service.impl;

import com.ecommerce.ecommerce_20.core.ApiResponse;
import com.ecommerce.ecommerce_20.dto.Request.AddCartItemsRequest;
import com.ecommerce.ecommerce_20.dto.Request.CartRequest;
import com.ecommerce.ecommerce_20.dto.Response.cart.CartItemsListResponse;
import com.ecommerce.ecommerce_20.entity.customer.Cart;
import com.ecommerce.ecommerce_20.entity.customer.CartItems;
import com.ecommerce.ecommerce_20.entity.customer.auth.Customers;
import com.ecommerce.ecommerce_20.entity.product.Products;
import com.ecommerce.ecommerce_20.mapper.CartsMapper;
import com.ecommerce.ecommerce_20.repository.CartItemsRepo;
import com.ecommerce.ecommerce_20.repository.CartRepo;
import com.ecommerce.ecommerce_20.repository.CustomersRepo;
import com.ecommerce.ecommerce_20.repository.ProductsRepo;
import com.ecommerce.ecommerce_20.service.CartService;
import com.ecommerce.ecommerce_20.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductsRepo productsRepo;
    private final CustomersRepo customersRepo;
    private final CartRepo cartRepo;
    private final CartItemsRepo cartItemsRepo;
    private final CartsMapper cartsMapper;

    @Override
    public ApiResponse<?> addItemToCart(AddCartItemsRequest cartItemsRequest, Principal principal) {
        try {
            if (cartItemsRequest.getQuantity() <= 0) {
                return ResponseUtil.getSuccessMessage(-1, "Quantity must be greater than 0");
            }
            Customers loadUser = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));

            Products findProductById = productsRepo.findByUniqueId(cartItemsRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));


            Cart cart = cartRepo.findByCustomers(loadUser).orElseGet(() ->
                    {
                        Cart newCart = new Cart();
                        newCart.setCustomers(loadUser);
                        newCart.setCartItems(new ArrayList<>());
                        return cartRepo.save(newCart);
                    }
            );
            Optional<CartItems> existingItems = cart.getCartItems()
                    .stream()
                    .filter(item -> !item.getIsDeleted())
                    .filter(item -> item.getProduct().getUniqueId().equals(cartItemsRequest.getProductId()))
                    .findFirst();

            if (existingItems.isPresent()) {
                CartItems cartItems = existingItems.get();
                cartItems.setQuantity(cartItems.getQuantity() + cartItemsRequest.getQuantity());
                cartItemsRepo.save(cartItems);
            } else {
                CartItems cartItems = new CartItems();
                cartItems.setCart(cart);
                cartItems.setQuantity(cartItemsRequest.getQuantity());
                cartItems.setProduct(findProductById);
                cart.getCartItems().add(cartItems);
                cartItemsRepo.save(cartItems);

            }
            return ResponseUtil.getSuccessMessage(0, "Successfully added item to the cart");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ApiResponse<?> getCartItems(Principal principal) {
        try {
            Customers customer = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Optional<Cart> itemsLists = cartRepo.findByCustomers(customer);
            List<CartItems> allItemsList = cartItemsRepo.findByCartAndIsDeletedFalse(itemsLists.get());
            if (itemsLists.isPresent()) {
                List<CartItemsListResponse> cartResponseList = cartsMapper.getCartResponseList(allItemsList);
                return ResponseUtil.getSuccessMessage(0, "Successfully retrieved cart items", cartResponseList);
            }
            return ResponseUtil.getSuccessMessage(0, "No cart items found");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public ApiResponse<?> removeItemFromCart(CartRequest cartRequest, Principal principal) {
        try {
            Customers customers = customersRepo.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Cart cart = cartRepo.findByCustomers(customers).orElseThrow(() -> new RuntimeException("Cart not found"));
            List<CartItems> cartItemsList = cartItemsRepo.findByCartAndIsDeletedFalse(cart);
//            for (CartItems item : cartItemsList) {
//                System.out.println("Item Product UniqueId = " + item.getProduct().getUniqueId());
//            }
//            System.out.println("Request UniqueId = " + cartRequest.getUniqueId());


            Optional<CartItems> items = cartItemsList
                    .stream()
                    .filter(item -> item.getProduct().getUniqueId().equals(cartRequest.getUniqueId()))
                    .findFirst();

            if (items.isEmpty()) {
                return ResponseUtil.getSuccessMessage(-1, "Item not found");
            }
            CartItems cartItems = items.get();
            cartItems.setIsDeleted(true);
            cartItemsRepo.save(cartItems);

            return ResponseUtil.getSuccessMessage(0, "Successfully removed item from the cart");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
