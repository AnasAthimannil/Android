package com.vlz.eecommerce.service.repository.dao;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.vlz.eecommerce.model.ProductBean;
import com.vlz.eecommerce.utils.ObjectFactory;

import java.util.List;

public class RoomDatabase {

    private ProductDao dao;

    public RoomDatabase(Application application){
        dao = ObjectFactory.getAppDatabaseInstance(application).productDao();
    }

    public void insertProducts(List<ProductBean> productList){
        dao.deleteAllProducts();
        dao.insertProducts(productList);
    }

    public LiveData<List<ProductBean>> getAllProducts(){
        return dao.getProducts();
    }
}
