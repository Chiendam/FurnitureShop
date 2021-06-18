package com.example.furnitureshop.service;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServieImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Category storeCategory(Category category) {
        if(null != category){
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public boolean removeCategory(int id) {
        if (0 < categoryRepository.checkDeleteCategory(id)) {
            return false;
        } else {
            Category category = categoryRepository.getById(id);
            categoryRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public Category updateCategory(int id, Category category) {
        Category categoryCurrent = categoryRepository.getById(id);
        if(null != categoryCurrent){
            categoryCurrent.setName(category.getName());
            categoryCurrent.setDescription(category.getDescription());
        }
        return categoryRepository.save(categoryCurrent);
    }

    @Override
    public Category detailCategory(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public long getLengthCategory() {
        return categoryRepository.count();
    }

    @Override
    public List<Category> getByPage(int start, int numberRecord) {
        return categoryRepository.getByPage(start, numberRecord);
    }

    @Override
    public boolean checkNameAdd(String name) {
        return categoryRepository.checkNameAdd(name) > 0 ? false : true;
    }

    @Override
    public boolean checkNameEdit(String name, int id) {
        return categoryRepository.checkNameEdit(name, id) > 0 ? false : true;
    }

    @Override
    public List<Category> searchByName(String keySearch, int start, int numberRecord) {
        return categoryRepository.searchByName(keySearch, start, numberRecord);
    }

    @Override
    public int getCountItemSearch(String keySearch) {
        return categoryRepository.getCountItemSearch(keySearch);
    }

    @Override
    public boolean checkDeleteCategory(int categoryId) {
        return false;
    }
}
