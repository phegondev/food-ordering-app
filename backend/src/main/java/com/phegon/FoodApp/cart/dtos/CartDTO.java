package com.phegon.FoodApp.cart.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDTO {

    private Long id;
    private List<CartItemDTO> cartItems;
    private Long menuId;
    private int quantity;
    private BigDecimal totalAmount;

}
