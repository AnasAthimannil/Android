package com.vlz.eecommerce.service.repository.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vlz.eecommerce.model.ProductBean;

@Database(entities = ProductBean.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
