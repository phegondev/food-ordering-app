package com.phegon.FoodApp.cart.services;

import com.phegon.FoodApp.cart.dtos.CartDTO;
import com.phegon.FoodApp.response.Response;

public interface CartService {

    Response<?> addItemToCart(CartDTO cartDTO);
    Response<?> incrementItem(Long menuId);
    Response<?> decrementItem(Long menuId);
    Response<?> removeItem(Long cartItemId);
    Response<CartDTO> getShoppingCart();
    Response<?> clearShoppingCart();
}
