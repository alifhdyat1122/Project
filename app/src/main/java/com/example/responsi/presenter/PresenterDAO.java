package com.example.responsi.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.responsi.entity.AppDatabase;
import com.example.responsi.entity.DataNews;
import com.example.responsi.view.MainContact;

public class PresenterDAO implements MainContact.datapresenter {
    MainContact.view view;
    MainContact.hapus viewH;

    public PresenterDAO(MainContact.view view) {
        this.view = view;
    }

    public PresenterDAO(MainContact.hapus viewH) {
        this.viewH = viewH;
    }


    @Override
    public void deleteData(AppDatabase database, DataNews dataNews) {
        new DeleteData(database, dataNews).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void> {
        private AppDatabase database;
        private DataNews dataNews;
        Context context;
        public DeleteData(AppDatabase database, DataNews dataNews){
            this.database = database;
            this.dataNews = dataNews;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataNews);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses();
        }
    }
}
