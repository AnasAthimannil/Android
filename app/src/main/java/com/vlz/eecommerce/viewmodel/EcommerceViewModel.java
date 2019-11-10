package com.vlz.eecommerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vlz.eecommerce.model.ProductBean;
import com.vlz.eecommerce.service.EcommerceService;

import java.util.List;

public class EcommerceViewModel extends AndroidViewModel {

    private EcommerceService service;
    public EcommerceViewModel(@NonNull Application application) {
        super(application);
        service = new EcommerceService(application);
    }

    // getProducts
    public LiveData<List<ProductBean>> getProducts(){
        return service.getProducts();
    }
}
