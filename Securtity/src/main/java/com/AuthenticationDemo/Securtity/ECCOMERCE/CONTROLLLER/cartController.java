package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.CartItem;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.User;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.cartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/cart")
@RequiredArgsConstructor
public class cartController {

    private final cartServices cartServices;

    @GetMapping("/get")
    public List<CartItem> getAllCartItems(@AuthenticationPrincipal User user) {
        return cartServices.getAllCartItems(user);
    }


    @PostMapping("/add/{productId}")
    public CartItem addToCart(@AuthenticationPrincipal User user, @PathVariable Integer productId) {
        return cartServices.addToProductToCart(user, productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void removeFromCartItem(@AuthenticationPrincipal User user, @PathVariable Integer productId) {
        cartServices.removeFromCart(user, productId);
    }

    @PutMapping("/increaseQuantity/{cartId}")
    public void increaseQuantity(@AuthenticationPrincipal User user, @PathVariable Long cartId) {
        cartServices.increaseQuantity(user, cartId);
    }

    @PutMapping("/decreaseQuantity/{cartId}")
    public void decreaseQuantity(@AuthenticationPrincipal User user, @PathVariable Long cartId) {
        cartServices.decreaseQuantity(user, cartId);
    }
}
