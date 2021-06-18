package com.example.furnitureshop.service;

import com.example.furnitureshop.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserService {
    public List<User> getAllUser();

    public User storeUser(User user);

    public boolean removeUser(int id);

    public User updateUser(int id, User user);

    public User detailUser(int id);

    public long getLengthUser();

    public List<User> getByPage(int start, int numberRecord);

    public boolean checkEmailAdd(String email);

    public boolean checkEmailEdit(String email, int id);

    public List<User> searchByName(String keySearch, int start, int numberRecord);

    public int getCountItemSearch(String keySearch);

}
