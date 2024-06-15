package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.CartItem;
import com.AuthenticationDemo.Securtity.ECCOMERCE.SERVICES.cartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v3/cart")
@RequiredArgsConstructor
public class cartController {

   private final cartServices  cartServices;

    @GetMapping("/get")
    public List<CartItem>getAllCartItems(){
      return   cartServices.getAllCartItems();

    }
    @PostMapping("/add/{productId}")
    public CartItem addToCart(@PathVariable Integer productId) {
        return cartServices.addToProductToCart(productId);
    }
    @DeleteMapping("/delete/{productId}")
    public void removeFromCartItem(@PathVariable Integer productId) {
        cartServices.removeFromCart(productId);
    }

}
