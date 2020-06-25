package com.benny.submissionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView rvBarang;
    private ArrayList<StoreData> list = new ArrayList<>();
    View aboutActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBarang = (RecyclerView) findViewById(R.id.cycler_home);
        rvBarang.setHasFixedSize(true);

        list.addAll(BarangData.getListData());
        showRecyclerGrid();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        switch (itemId){
            case R.id.tentangg:
                Intent intent = new Intent(MainActivity.this,AboutFragment.class);
                startActivity(intent);
                break;
            case R.id.grid_View:
            showRecyclerGrid();
            break;
            case R.id.view_card:
            showRecyclerCard();
            break;
        }
    }

    private void showRecyclerCard() {
        rvBarang.setLayoutManager(new LinearLayoutManager(this));
        BarangAdapter barangAdapter = new BarangAdapter(MainActivity.this,list);
        rvBarang.setAdapter(barangAdapter);
    }

    private void showRecyclerGrid() {
        rvBarang.setLayoutManager(new GridLayoutManager(this,2));
        GridAdapter gridAdapter = new GridAdapter(MainActivity.this, list);
        rvBarang.setAdapter(gridAdapter);
    }
    //    public boolean onOptionItemSelected(MenuItem item){
//        setMode();
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setMode(int itemId) {
//        switch ()
//    }
//    private boolean loadFragment(Fragment fragment){
//        if(fragment != null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragment).commit();
//            return true;
//        }
//        return false;
//    }



}
