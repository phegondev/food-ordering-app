package com.phegon.FoodApp.menu.entity;


import com.phegon.FoodApp.category.entity.Category;
import com.phegon.FoodApp.order.entity.OrderItem;
import com.phegon.FoodApp.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "menus")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id") // Link to the Category entity
    private Category category;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "menu")
    private List<Review> reviews;


}
