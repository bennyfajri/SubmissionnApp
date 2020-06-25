package com.benny.submissionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetailActivity extends Activity {

    TextView nama;
    TextView deskripsi;
    TextView harga;
    Button btnBeli;
    CarouselView carouselView;
    RecyclerView rvLainnya;
    ArrayList<StoreData>list = new ArrayList<>();
    EditText edtJumlah;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    int pricee;
    Locale localeID;
    NumberFormat formatRupiah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama = findViewById(R.id.txt_detail_nama);
        deskripsi = findViewById(R.id.txt_detail_deskripsi);
        harga = findViewById(R.id.txt_detail_harga);
        carouselView = findViewById(R.id.img_detail);
        btnBeli = findViewById(R.id.btn_Beli);
        rvLainnya = findViewById(R.id.rvLainnya);
        rvLainnya.setHasFixedSize(true);

        rvLainnya.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        list.addAll(BarangData.getListData());
        GridAdapter gridAdapter = new GridAdapter(DetailActivity.this,list);
        rvLainnya.setAdapter(gridAdapter);

        Bundle bundle = getIntent().getExtras();
        final int[] images = {bundle.getInt("Gambar"),bundle.getInt("Gambar2"),bundle.getInt("Gambar3")};
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });
        pricee = bundle.getInt("Harga");
        localeID = new Locale("in","ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        if(bundle != null){
            nama.setText(bundle.getString("Nama"));
            deskripsi.setText(bundle.getString("Deskripsi"));
            harga.setText(formatRupiah.format((double)pricee));
        }

       btnBeli.setOnClickListener(new FormDialog());
    }

    private void kosong() {
        edtJumlah.setText(null);
    }
    private class FormDialog implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dialog = new AlertDialog.Builder(DetailActivity.this);
            inflater = getLayoutInflater();
            dialogView = inflater.inflate(R.layout.jumlah,null);
            dialog.setView(dialogView);
            dialog.setCancelable(true);
            dialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
            dialog.setTitle("Jumlah Barang");

            edtJumlah =(EditText) dialogView.findViewById(R.id.edt_Jumlah);
            kosong();


            dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    double jumlah1 = Double.parseDouble(edtJumlah.getText().toString());
                    final double subtotal = pricee * jumlah1;
                    double diskon = 0;
                    if (jumlah1 >= 3){
                        diskon = subtotal * 0.1;
                    }
                    final double pajak = 0.02 * subtotal;
                    final double total = subtotal - diskon + pajak;
                    Intent intent = new Intent(DetailActivity.this,OutputActivity.class);
                    intent.putExtra("Output",
                            "Nama Barang : "+nama.getText().toString()+
                                    "\nHarga : "+harga.getText().toString()+
                                    "\nJumlah : "+edtJumlah.getText().toString()+
                                    "\nSub Total : "+formatRupiah.format((double)subtotal)+
                                    "\nDiskon : "+formatRupiah.format((double)diskon)+
                                    "\nPajak 2% : "+formatRupiah.format((double)pajak)+
                                    "\nTotal Pembayaran : "+formatRupiah.format((double)total));
                    startActivity(intent);
                }
            });




            dialog.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }


}
