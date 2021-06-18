package com.example.furnitureshop.repository;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.model.Product;
import com.example.furnitureshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<Product> getByPage(@Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select * from products where name like concat('%',:keySearch,'%') ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<Product> searchByName(@Param("keySearch")String keySearch, @Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select count(*) from products where name like concat('%',:keySearch,'%')", nativeQuery = true)
    public int getCountItemSearch(@Param("keySearch")String keySearch);
}
