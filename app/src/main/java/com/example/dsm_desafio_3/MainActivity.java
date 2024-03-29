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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHelper databaseHelper;
    ArrayList<String> id, model, vin, chassis, motor, seats, year, price, brand, color;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.register_user){
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.logout){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    void storeDataInArrays() {
        Cursor cursor = databaseHelper.getAllCars();
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
            customAdapter = new CustomAdapter(MainActivity.this, this, id, model, vin, chassis, motor, seats, year, price, brand, color);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }
}