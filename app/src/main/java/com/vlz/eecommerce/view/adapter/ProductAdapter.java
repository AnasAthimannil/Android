package com.vlz.eecommerce.view.adapter;

import android.app.Application;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vlz.eecommerce.R;
import com.vlz.eecommerce.model.ProductBean;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<ProductBean> productList;
    private Application application;

    public ProductAdapter(List<ProductBean> productList, Application application){
        this.productList = productList;
        this.application = application;
    }
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        ProductBean product = productList.get(position);
        Glide.with(application)
                .load(product.getImagePath())
                .into(holder.productImage);

        holder.productName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public long getItemId(int position) {
        if(productList.size() < position)
            return productList.get(position).getId();
        return RecyclerView.NO_ID;
    }

    class ProductHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productName;

        public ProductHolder(@NonNull View view) {
            super(view);

            productImage = view.findViewById(R.id.productImage);
            productName = view.findViewById(R.id.productName);
        }
    }
}
