package com.vlz.eecommerce.service.repository.webservice;

import com.vlz.eecommerce.model.ProductBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EcommerceAPI {

    @GET("GetItemCategory")
    Call<List<ProductBean>> getProducts();
}
