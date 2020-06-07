package com.example.responsi.entity;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DataNewsDAO {
    @Insert
    long insertData(DataNews dataNews);

    @Query("Select * from newsdb")
    List<DataNews> getData();

    @Update
    int updateData(DataNews item);

    @Delete
    void deleteData(DataNews item);
}