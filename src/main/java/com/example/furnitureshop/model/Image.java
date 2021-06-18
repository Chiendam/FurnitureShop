package com.example.furnitureshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "path", columnDefinition = "varchar(255)")
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
