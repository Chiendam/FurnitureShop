package com.example.furnitureshop.service;

import com.example.furnitureshop.model.User;
import com.example.furnitureshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public User storeUser(User user) {
        if(null != user){
                return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean removeUser(int id) {
        User user = userRepository.getById(id);
        if(null != user){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(int id, User user) {
        System.out.println(user.getFullName());
        User userCurrent = userRepository.getById(id);
        System.out.println(userCurrent.getFullName());
        if(null != userCurrent){
            userCurrent.setFullName(user.getFullName());
            userCurrent.setAvatar(user.getAvatar());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setPhone(user.getPhone());
            userCurrent.setRole(user.getRole());
            userCurrent.setPassword(user.getPassword());
            System.out.println(userCurrent.getFullName());
        }
        return userRepository.save(userCurrent);
    }

    @Override
    public User detailUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public long getLengthUser() {
        return userRepository.count();
    }

    @Override
    public List<User> getByPage(int start, int numberRecord) {
        return userRepository.getByPage(start, numberRecord);
    }

    @Override
    public boolean checkEmailAdd(String email) {
        return userRepository.checkEmailAdd(email) > 0 ? false : true;
    }

    @Override
    public boolean checkEmailEdit(String email, int id) {
        return userRepository.checkEmailEdit(email, id) > 0 ? false : true;
    }

    @Override
    public List<User> searchByName(String keySearch, int start, int numberRecord) {
        return userRepository.searchByName(keySearch, start, numberRecord);
    }

    @Override
    public int getCountItemSearch(String keySearch) {
        return userRepository.getCountItemSearch(keySearch);
    }
}
