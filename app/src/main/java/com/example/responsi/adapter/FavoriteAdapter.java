package com.example.responsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.responsi.R;
import com.example.responsi.entity.AppDatabase;
import com.example.responsi.entity.DataNews;
import com.example.responsi.view.MainContact;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {

    private AppDatabase appDatabase;
    private Context context;
    private ArrayList<DataNews> list;
    private MainContact.hapus view;

    public FavoriteAdapter(Context context, ArrayList<DataNews> list, MainContact.hapus view) {
        this.context = context;
        this.list = list;
        this.view = view;
    }

    @NonNull
    @Override
    public FavoriteAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite, viewGroup,false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.Holder holder, int i) {
        holder.bind(i);
    }
    private ImageView ivFavorit;
    private TextView tvTitlefav;
    private CardView cvItemFav;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitlefav = itemView.findViewById(R.id.itemfavorit_tv_tittle);
            cvItemFav = itemView.findViewById(R.id.itemfavorit_cv);
            ivFavorit = itemView.findViewById(R.id.itemfavorit_star_favorit);
        }

        public void bind(int i){
            final DataNews dataNews = list.get(i);
            tvTitlefav.setText(dataNews.getNewsName());
            ivFavorit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appDatabase = AppDatabase.iniDb(context);

                    view.deleteData(dataNews);
                }
            });

        }
    }
}
