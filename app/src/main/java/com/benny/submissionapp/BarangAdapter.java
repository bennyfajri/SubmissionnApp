package com.benny.submissionapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    public Context context;
    public ArrayList<StoreData> listData;
    public BarangAdapter(Context context,ArrayList<StoreData> storeData){
        this.context = context;
        this.listData = storeData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final StoreData storeData = listData.get(position);
        Glide.with(holder.itemView.getContext())
                .load(storeData.getImage())
                .apply(new RequestOptions().override(350,350))
                .into(holder.image);
        holder.nama.setText(storeData.getNama());
        holder.deskripsi.setText(storeData.getDeskripsi());
        Locale localeID = new Locale("in","ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.harga.setText(formatRupiah.format(storeData.getHarga()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("Nama",listData.get(holder.getAdapterPosition()).getNama());
                intent.putExtra("Deskripsi",listData.get(holder.getAdapterPosition()).getDeskripsi());
                intent.putExtra("Harga",listData.get(holder.getAdapterPosition()).getHarga());
                intent.putExtra("Gambar",listData.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Gambar2",listData.get(holder.getAdapterPosition()).getImage2());
                intent.putExtra("Gambar3",listData.get(holder.getAdapterPosition()).getImage3());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nama;
        TextView deskripsi;
        TextView harga;
        CardView cardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_item_photo);
            nama = itemView.findViewById(R.id.txt_item_name);
            deskripsi = itemView.findViewById(R.id.txt_item_detail);
            harga = itemView.findViewById(R.id.txt_item_harga);
            cardView = itemView.findViewById(R.id.card_view);

        }
    }
}
