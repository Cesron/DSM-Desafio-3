package com.example.dsm_desafio_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText modelInput, vinInput, chassisInput, motorInput, seatsInput, yearInput, priceInput, brandInput, colorInput;

    Button updateButton, deleteButton;

    String id, model, vin, chassis, motor, seats, year, price, brand, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        modelInput = findViewById(R.id.model);
        vinInput = findViewById(R.id.vin);
        chassisInput = findViewById(R.id.chassis);
        motorInput = findViewById(R.id.motor);
        seatsInput = findViewById(R.id.seats);
        yearInput = findViewById(R.id.year);
        priceInput = findViewById(R.id.price);
        brandInput = findViewById(R.id.brand);
        colorInput = findViewById(R.id.color);

        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                Boolean updateCar = db.updateCar(id, modelInput.getText().toString().trim(),
                        vinInput.getText().toString().trim(),
                        chassisInput.getText().toString().trim(),
                        motorInput.getText().toString().trim(),
                        seatsInput.getText().toString().trim(),
                        yearInput.getText().toString().trim(),
                        priceInput.getText().toString().trim(),
                        brandInput.getText().toString().trim(),
                        colorInput.getText().toString().trim());

                if (updateCar == true) {
                    Toast.makeText(UpdateActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateActivity.this, "Car update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
            modelInput.setText(model);
            vinInput.setText(vin);
            chassisInput.setText(chassis);
            motorInput.setText(motor);
            seatsInput.setText(seats);
            yearInput.setText(year);
            priceInput.setText(price);
            brandInput.setText(brand);
            colorInput.setText(color);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Delete " + model + " ?");
        builder.setMessage("Are you sure you want to delete " + model + " ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
            db.deleteCar(id);

            Boolean deleteCar = db.deleteCar(id);

            if (deleteCar == true) {
                Toast.makeText(UpdateActivity.this, "Car deleted successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(UpdateActivity.this, "Car delete failed", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }

}