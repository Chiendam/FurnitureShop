package com.example.furnitureshop.service;

import com.example.furnitureshop.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService {
    public List<Category> getAllCategory();

    public Category storeCategory(Category category);

    public boolean removeCategory(int id);

    public Category updateCategory(int id, Category category);

    public Category detailCategory(int id);

    public long getLengthCategory();

    public List<Category> getByPage(int start, int numberRecord);

    public boolean checkNameAdd(String name);

    public boolean checkNameEdit(String name, int id);

    public List<Category> searchByName(String keySearch, int start, int numberRecord);

    public int getCountItemSearch(String keySearch);

    public boolean checkDeleteCategory(int categoryId);
}
