package com.example.dsm_desafio_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CarActivity extends AppCompatActivity {

    TextView modelTxt, vinTxt, chassisTxt, motorTxt, seatsTxt, yearTxt, priceTxt, brandTxt, colorTxt;
    String id, model, vin, chassis, motor, seats, year, price, brand, color, email;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        modelTxt = findViewById(R.id.model_txt);
        vinTxt = findViewById(R.id.vin_txt);
        chassisTxt = findViewById(R.id.chassis_txt);
        motorTxt = findViewById(R.id.motor_txt);
        seatsTxt = findViewById(R.id.seats_txt);
        yearTxt = findViewById(R.id.year_txt);
        priceTxt = findViewById(R.id.price_txt);
        brandTxt = findViewById(R.id.brand_txt);
        colorTxt = findViewById(R.id.color_txt);

        addButton = findViewById(R.id.add_fav_button);

        getAndSetIntentData();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(CarActivity.this);
                email = db.getSession();
                Toast.makeText(CarActivity.this, email, Toast.LENGTH_SHORT).show();
                Boolean addCar = db.insertFavoriteCar(email, model, vin, chassis, motor, seats, year, price, brand, color);

                if (addCar == true) {
                    Toast.makeText(CarActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CarActivity.this, ClientActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CarActivity.this, "Car add failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("model") &&
                getIntent().hasExtra("vin") && getIntent().hasExtra("chassis") &&
                getIntent().hasExtra("motor") && getIntent().hasExtra("seats") &&
                getIntent().hasExtra("year") && getIntent().hasExtra("price") &&
                getIntent().hasExtra("brand") && getIntent().hasExtra("color")) {
            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            model = getIntent().getStringExtra("model");
            vin = getIntent().getStringExtra("vin");
            chassis = getIntent().getStringExtra("chassis");
            motor = getIntent().getStringExtra("motor");
            seats = getIntent().getStringExtra("seats");
            year = getIntent().getStringExtra("year");
            price = getIntent().getStringExtra("price");
            brand = getIntent().getStringExtra("brand");
            color = getIntent().getStringExtra("color");

            // Setting Intent Data
            modelTxt.setText(model);
            vinTxt.setText(vin);
            chassisTxt.setText(chassis);
            motorTxt.setText(motor);
            seatsTxt.setText(seats);
            yearTxt.setText(year);
            priceTxt.setText(price);
            brandTxt.setText(brand);
            colorTxt.setText(color);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}