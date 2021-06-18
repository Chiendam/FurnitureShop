package com.example.furnitureshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", columnDefinition = "varchar(50)")
    private String customerFirstName;

    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String customerLastName;

    @Column(name = "phone", columnDefinition = "varchar(12)")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(100)")
    private String email;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "city", columnDefinition = "varchar(100)")
    private String city;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @Column(name = "status", columnDefinition = "integer default 0 comment 'trang thai 0 don dat hang chua đuoc duyet, 1 la da đuoc duyet, 2 đon hang ban thanh cong' ")
    private int status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_time", columnDefinition = "DATETIME")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME")
    private Date updateTime;

}
