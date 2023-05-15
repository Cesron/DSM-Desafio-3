package com.example.dsm_desafio_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHelper databaseHelper;
    ArrayList<String> id, model, vin, chassis, motor, seats, year, price, brand, color;
    FavoritesAdapter favoritesAdapter;
    String session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        databaseHelper = new DatabaseHelper(FavoritesActivity.this);
        id = new ArrayList<>();
        model = new ArrayList<>();
        vin = new ArrayList<>();
        chassis = new ArrayList<>();
        motor = new ArrayList<>();
        seats = new ArrayList<>();
        year = new ArrayList<>();
        price = new ArrayList<>();
        brand = new ArrayList<>();
        color = new ArrayList<>();

        storeDataInArrays();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cars_listing) {
            Intent intent = new Intent(FavoritesActivity.this, ClientActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(FavoritesActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    void storeDataInArrays() {
        session = databaseHelper.getSession();

        Cursor cursor = databaseHelper.getFavoriteCars(session);
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(android.view.View.VISIBLE);
            no_data.setVisibility(android.view.View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                model.add(cursor.getString(1));
                vin.add(cursor.getString(2));
                chassis.add(cursor.getString(3));
                motor.add(cursor.getString(4));
                seats.add(cursor.getString(5));
                year.add(cursor.getString(6));
                price.add(cursor.getString(7));
                brand.add(cursor.getString(8));
                color.add(cursor.getString(9));
            }
            empty_imageview.setVisibility(android.view.View.GONE);
            no_data.setVisibility(android.view.View.GONE);
            favoritesAdapter = new FavoritesAdapter(FavoritesActivity.this, this, id, model, vin, chassis, motor, seats, year, price, brand, color);
            recyclerView.setAdapter(favoritesAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this));
        }
    }
}