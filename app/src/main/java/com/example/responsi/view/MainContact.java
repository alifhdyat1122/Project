package com.example.responsi.view;

import android.view.View;

import com.example.responsi.entity.AppDatabase;
import com.example.responsi.entity.DataNews;

import java.util.ArrayList;

public interface MainContact {
    interface view extends View.OnClickListener {
        void resetForm();

        void sukses();

        void editData(DataNews item);

    }

    interface datapresenter {

        void deleteData(AppDatabase database, DataNews dataNews);
    }

    interface Cetak extends View.OnClickListener {
        void getData(ArrayList<DataNews> list);
    }

    interface hapus {

        void sukses();

        void deleteData(DataNews item);
    }
}
