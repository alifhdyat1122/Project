package com.example.responsi.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newsdb")
public class DataNews {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "newsName")
    private String newsName;

    @ColumnInfo(name = "newsSource")
    private String newsSource;

    @ColumnInfo(name = "newsDes")
    private String newsDes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsDes() {
        return newsDes;
    }

    public void setNewsDes(String newsDes) {
        this.newsDes = newsDes;
    }
}
