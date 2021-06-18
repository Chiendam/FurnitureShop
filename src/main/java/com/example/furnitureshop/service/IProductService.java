package com.example.furnitureshop.service;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService {
    public List<Product> getAll();

    public Product storeProduct(Product product);

    public boolean removeProduct(int id);

    public Product updateProduct(int id, Product product);

    public Product detailProduct(int id);

    public List<Product> getByPage(int start, int numberRecord);

    public List<Product> searchByName(String keySearch, int start, int numberRecord);

    public int getCountItemSearch(String keySearch);

    public int getCountItem();

}
