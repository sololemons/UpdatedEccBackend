package com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.CartItem;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.cartRepository;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.productRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class cartServices {


    private final cartRepository cartRepository;

    private final productRepository productRepository;



    public List<CartItem> getAllCartItems() {

   return  cartRepository.findAll();
    }
    public CartItem addToProductToCart(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartRepository.findByProduct_ProductId(productId)
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        }

        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Integer productId) {
        CartItem cartItem = cartRepository.findByProduct_ProductId(productId)
                .orElse(null);

        if (cartItem != null) {
            cartRepository.delete(cartItem);
        } else {
            throw new RuntimeException("CartItem not found for product ID: " + productId);
        }
    }
}



