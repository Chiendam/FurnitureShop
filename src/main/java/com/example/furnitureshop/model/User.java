package com.example.furnitureshop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Không được trống")
    @Column(name = "full_name", columnDefinition = "varchar(50)")
    private String fullName;

    @NotBlank(message = "Không được trống")
    @Size(min = 9, max = 12, message = "Vui lòng nhập đúng định dạng số điện thoại")
    @Column(name = "phone", columnDefinition = "varchar(12)")
    private String phone;

    @NotBlank(message = "Không được trống")
    @Email(message = "Lỗi định dạng mail")
    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @NotBlank(message = "Không được trống")
    @Column(name = "password", columnDefinition = "varchar(50)")
    private String password;

    @Column(name = "avatar", columnDefinition = "varchar(50)")
    private String avatar;

//    @NotBlank(message = "Không được trống")
    @Column(name = "role", columnDefinition = "integer default 0 comment 'Trang thai 0 là khach hang, 1 la quan tri'" )
    private int role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User() {
    }

    public User(int id, String fullName, String phone, String email, String password, String avatar, int role) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
