package com.example.furnitureshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_time", columnDefinition = "DATETIME")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
