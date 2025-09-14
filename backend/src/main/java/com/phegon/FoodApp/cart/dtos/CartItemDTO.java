package com.phegon.FoodApp.cart.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegon.FoodApp.menu.dtos.MenuDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemDTO {


    private Long id;

    private MenuDTO menu;

    private int quantity;

    private BigDecimal pricePerUnit;

    private BigDecimal subtotal;
}
