package com.vlz.eecommerce.service;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vlz.eecommerce.model.ProductBean;
import com.vlz.eecommerce.service.repository.dao.RoomDatabase;
import com.vlz.eecommerce.service.repository.webservice.EcommerceAPI;
import com.vlz.eecommerce.service.repository.webservice.WebApi;
import com.vlz.eecommerce.utils.CommonUtils;

import java.util.List;

public class EcommerceService {

    private Application application;
    private RoomDatabase roomDb;
    private WebApi webApi;

    public EcommerceService(Application application){
        this.application = application;
        roomDb = new RoomDatabase(application);
        webApi = new WebApi();
    }

    public LiveData<List<ProductBean>> getProducts(){
        if(!CommonUtils.isNetworkAvailable(application)){
            return roomDb.getAllProducts();
        }
        else{
            final MutableLiveData<List<ProductBean>> liveData = new MutableLiveData<>();
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductBean> res = webApi.getProducts();
                    if(res != null && res.size() > 0){
                        roomDb.insertProducts(res);
                        liveData.postValue(res);
                    }
                    else
                        liveData.postValue(null);
                }
            });
            return liveData;
        }
    }
}
