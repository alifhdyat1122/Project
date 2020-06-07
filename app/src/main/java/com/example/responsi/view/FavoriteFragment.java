package com.example.responsi.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.responsi.R;
import com.example.responsi.adapter.FavoriteAdapter;
import com.example.responsi.entity.AppDatabase;
import com.example.responsi.entity.DataNews;
import com.example.responsi.presenter.PresenterDAO;
import com.example.responsi.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements MainContact.hapus {
    private String api_key = "d2890b74ad774387bbf8258f8996075d";
    private String country = "US";
    private String year = "2020";

    private RecyclerView A;
    private Adapter adapter;
    private AppDatabase appDatabase;
    private PresenterDAO presenterDAO;
    private FavoriteAdapter favoriteAdapter;
    Context context;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        A = view.findViewById(R.id.fragmentfavorit_rv);

        presenterDAO = new PresenterDAO(this);
        A.setLayoutManager(new LinearLayoutManager(context));
        appDatabase = AppDatabase.iniDb(getContext());
        readData(appDatabase);
    }

    public void readData(AppDatabase database) {
        List list;
        list = database.dao().getData();
        favoriteAdapter = new FavoriteAdapter(context, (ArrayList<DataNews>) list, this);
        A.setAdapter(favoriteAdapter);
    }

    @Override
    public void sukses() {
        Toast.makeText(getContext(), "unlike", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void deleteData(final DataNews item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Hapus Data")
                .setMessage("Ingin Hapus dari Favorit ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenterDAO.deleteData(appDatabase, item);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}





