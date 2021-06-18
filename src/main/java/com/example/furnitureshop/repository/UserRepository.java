package com.example.furnitureshop.repository;

import com.example.furnitureshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select count(*) from users where email = :email", nativeQuery = true)
    public int checkEmailAdd(@Param("email") String email);

    @Query(value = "select count(*) from users where email = :email AND id <> :id", nativeQuery = true)
    public int checkEmailEdit(@Param("email") String email, @Param("id") int id);

    @Query(value = "SELECT * FROM users ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<User> getByPage(@Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select * from users where full_name like concat('%',:keySearch,'%') or email like concat('%',:keySearch,'%') ORDER BY id LIMIT :start, :numberRecord", nativeQuery = true)
    public List<User> searchByName(@Param("keySearch")String keySearch, @Param("start") int start, @Param("numberRecord") int numberRecord);

    @Query(value = "select count(*) from users where full_name like concat('%',:keySearch,'%') or email like concat('%',:keySearch,'%')", nativeQuery = true)
    public int getCountItemSearch(@Param("keySearch")String keySearch);
}
