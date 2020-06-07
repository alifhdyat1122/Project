package com.example.responsi.adapter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.responsi.R;
import com.example.responsi.entity.AppDatabase;
import com.example.responsi.entity.DataNews;
import com.example.responsi.model.ArticlesItem;

import java.util.ArrayList;

//https://newsapi.org/v2/top-headlines?country=us&apiKey=d2890b74ad774387bbf8258f8996075d
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ArticlesItem> articlesItems = new ArrayList<>();
    private Context context;
    AppDatabase appDatabase;


    public Adapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ArticlesItem> items){
        articlesItems.clear();
        articlesItems.addAll(items);
        notifyDataSetChanged();
    }

    public ArrayList<ArticlesItem> getArticlesItems() {
        return articlesItems;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {
        final ArticlesItem item = articlesItems.get(position);
        Glide.with(context).load(articlesItems.get(position).getUrlToImage()).into(holder.ivimage);
        holder.tvtitle.setText(articlesItems.get(position).getTitle());
        holder.tvdesc.setText(articlesItems.get(position).getDescription());
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getArticlesItems().get(position).getAuthor());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });
        holder.ivimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, item.getUrl());
                context.startActivity(intent);
            }
        });
        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase = AppDatabase.iniDb(context);
                final DataNews dataNews = new DataNews();
                dataNews.setNewsName(articlesItems.get(position).getTitle());
                dataNews.setNewsDes(articlesItems.get(position).getDescription());
                dataNews.setNewsSource(articlesItems.get(position).getAuthor());
                new InsertData(appDatabase,dataNews).execute();

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivimage;
        TextView tvtitle, tvdesc;
        CardView cvitem;
        Button btn_share, btn_fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvitem = itemView.findViewById(R.id.item_list_cv);
            ivimage = itemView.findViewById(R.id.item_list_image);
            tvtitle = itemView.findViewById(R.id.item_list_title);
            tvdesc = itemView.findViewById(R.id.item_list_desc);
            btn_share = itemView.findViewById(R.id.share);
            btn_fav = itemView.findViewById(R.id.fav);

        }
    }
    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private  DataNews dataNews;


        public InsertData(AppDatabase database, DataNews dataNews) {
            this.database = database;
            this.dataNews = dataNews;

        }

        @Override
        protected Long doInBackground(Void... voids) {

            return database.dao().insertData(dataNews);
        }

        protected void onPostExecute(Long aLong){
            super.onPostExecute(aLong);
            Toast.makeText(context, "Favorit", Toast.LENGTH_SHORT).show();
        }
    }

}
