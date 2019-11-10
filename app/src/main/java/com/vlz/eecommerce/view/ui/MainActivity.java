package com.vlz.eecommerce.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.vlz.eecommerce.R;
import com.vlz.eecommerce.model.ProductBean;
import com.vlz.eecommerce.utils.ObjectFactory;
import com.vlz.eecommerce.view.adapter.ProductAdapter;
import com.vlz.eecommerce.viewmodel.EcommerceViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EcommerceViewModel ecommerceViewModel;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView productRecyclerView;
    private RecyclerView.Adapter productAdapter;
    private List<ProductBean> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        loadData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void initComponents(){
        if(ecommerceViewModel == null)
            ecommerceViewModel = ViewModelProviders.of(this).get(EcommerceViewModel.class);

        productList = new ArrayList<>();

        refreshLayout = findViewById(R.id.refreshLayout);
        productRecyclerView = findViewById(R.id.productRecyclerView);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(
                new GridLayoutManager(
                        getApplicationContext(),
                        3,
                        GridLayoutManager.VERTICAL,
                        false));
    }

    private void loadData(){
        refreshLayout.setRefreshing(true);
        ecommerceViewModel.getProducts().observe(this, new Observer<List<ProductBean>>() {
            @Override
            public void onChanged(List<ProductBean> res) {
                productList.clear();
                if(res != null && res.size() > 0)
                    productList.addAll(res);

                refreshLayout.setRefreshing(false);
                showProducts();
            }
        });
    }

    private void showProducts(){

        if(productAdapter == null){
            productAdapter = new ProductAdapter(productList, getApplication());
            productAdapter.setHasStableIds(true);
            productRecyclerView.setAdapter(productAdapter);
        }

        productAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        // nullify objects
        ObjectFactory.destroy();
        super.onDestroy();
    }
}
