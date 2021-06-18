package com.example.furnitureshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_details")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number_product")
    private int numberProduct;

    @Column(name = "sum_price", columnDefinition = "bigint")
    private int sum_price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
