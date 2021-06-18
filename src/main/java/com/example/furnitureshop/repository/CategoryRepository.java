package com.example.furnitureshop.repository;

import com.example.furnitureshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * FROM categories ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<Category> getByPage(@Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select count(*) from categories where name = :name", nativeQuery = true)
    public int checkNameAdd(@Param("name") String name);

    @Query(value = "select count(*) from categories where name = :name AND id <> :id", nativeQuery = true)
    public int checkNameEdit(@Param("name") String name, @Param("id") int id);

    @Query(value = "select * from categories where name like concat('%',:keySearch,'%') ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<Category> searchByName(@Param("keySearch")String keySearch, @Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select count(*) from categories where name like concat('%',:keySearch,'%')", nativeQuery = true)
    public int getCountItemSearch(@Param("keySearch")String keySearch);

    @Query(value = "SELECT COUNT(*) FROM products INNER JOIN categories ON products.category_id = categories.id WHERE categories.id = :categoryId", nativeQuery = true)
    public int checkDeleteCategory(@Param("categoryId")int categoryId);

}
