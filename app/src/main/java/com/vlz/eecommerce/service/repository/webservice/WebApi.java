package com.vlz.eecommerce.service.repository.webservice;

import com.vlz.eecommerce.model.ProductBean;
import com.vlz.eecommerce.service.repository.dao.ProductDao;
import com.vlz.eecommerce.utils.ObjectFactory;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class WebApi {

    private EcommerceAPI webApi;

    public WebApi(){
        webApi = ObjectFactory.getEcommerceAPIInstance();
    }

    // getProducts
    public List<ProductBean> getProducts(){
        try {

            Response<List<ProductBean>> res = webApi.getProducts().execute();
            if(res.isSuccessful())
                return res.body();
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
