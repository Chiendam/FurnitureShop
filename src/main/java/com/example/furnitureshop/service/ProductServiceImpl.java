package com.example.furnitureshop.service;

import com.example.furnitureshop.model.Product;
import com.example.furnitureshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product storeProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean removeProduct(int id) {
        Product product = productRepository.getById(id);
        if(null != product){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productCurrent = productRepository.getById(id);
        if(null != productCurrent){
            productCurrent.setName(product.getName());
            productCurrent.setCategory(product.getCategory());
            productCurrent.setImgPoster(product.getImgPoster());
            productCurrent.setPrice(product.getPrice());
            productCurrent.setDescription(product.getDescription());
            productCurrent.setMaterial(product.getMaterial());
            productCurrent.setPriceDiscount(product.getPriceDiscount());
            productCurrent.setSize(product.getSize());
            productCurrent.setStatus(product.getStatus());
        }
        return productRepository.save(productCurrent);
    }

    @Override
    public Product detailProduct(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getByPage(int start, int numberRecord) {
        return productRepository.getByPage(start, numberRecord);
    }

    @Override
    public List<Product> searchByName(String keySearch, int start, int numberRecord) {
        return productRepository.searchByName(keySearch,start, numberRecord);
    }

    @Override
    public int getCountItemSearch(String keySearch) {
        return productRepository.getCountItemSearch(keySearch);
    }

    @Override
    public int getCountItem() {
        return (int) productRepository.count();
    }
}
