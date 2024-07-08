package com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.CartItem;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.User;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.cartRepository;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.productRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class cartServices {

    private final cartRepository CartRepository;
    private final productRepository productRepository;

    public List<CartItem> getAllCartItems(User user) {
        return CartRepository.findByUserUserId(user.getUserId());
    }

    public CartItem addToProductToCart(User user, Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = CartRepository.findByUserUserIdAndProductProductId(user.getUserId(), productId)
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setUser(user);
        }

        return CartRepository.save(cartItem);
    }

    public void removeFromCart(User user, Integer productId) {
        CartItem cartItem = CartRepository.findByUserUserIdAndProductProductId(user.getUserId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        CartRepository.delete(cartItem);
    }

    public void increaseQuantity(User user, Long cartId) {
        CartItem cartItem = CartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized action");
        }

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        CartRepository.save(cartItem);
    }

    public void decreaseQuantity(User user, Long cartId) {
        CartItem cartItem = CartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized action");
        }

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            CartRepository.save(cartItem);
        } else {
            CartRepository.delete(cartItem);
        }
    }
}
