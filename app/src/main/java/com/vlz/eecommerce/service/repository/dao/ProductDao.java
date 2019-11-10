package com.vlz.eecommerce.service.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vlz.eecommerce.model.ProductBean;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert()
    void insertProducts(List<ProductBean> products);

    @Query("SELECT id, name, imagePath FROM products")
    LiveData<List<ProductBean>> getProducts();

    @Query("DELETE FROM PRODUCTS")
    void deleteAllProducts();

}
