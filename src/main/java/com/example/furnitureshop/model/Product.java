package com.example.furnitureshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Không được để trống")
    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "price", columnDefinition = "bigint")
    private int price;

    @Column(name = "price_discount", columnDefinition = "bigint")
    private int priceDiscount;

    @NotBlank(message = "Không được để trống")
    @Column(name = "size", columnDefinition = "varchar(255)")
    private String size;

    @NotBlank(message = "Không được để trống")
    @Column(name = "material", columnDefinition = "varchar(255)")
    private String material;

    @Column(name = "img_poster", columnDefinition = "varchar(100)")
    private String imgPoster;

    @NotBlank(message = "Không được để trống")
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "status", columnDefinition = "integer default 1 comment 'Trang thai 1 la con hang, 0 la het hang' ")
    private int status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private int categoryId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    @Column(name = "create_time", columnDefinition = "DATETIME")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME")
    private Date updateTime;

    public Product() {
    }

    public Product(int id, String name, int price, int priceDiscount, String size, String material, String imgPoster, String description, int status, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.size = size;
        this.material = material;
        this.imgPoster = imgPoster;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
