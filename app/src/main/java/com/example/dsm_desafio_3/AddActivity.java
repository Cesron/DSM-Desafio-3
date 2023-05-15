package com.example.dsm_desafio_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText modelInput, vinInput, chassisInput, motorInput, seatsInput, yearInput, priceInput, brandInput, colorInput;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        modelInput = findViewById(R.id.model);
        vinInput = findViewById(R.id.vin);
        chassisInput = findViewById(R.id.chassis);
        motorInput = findViewById(R.id.motor);
        seatsInput = findViewById(R.id.seats);
        yearInput = findViewById(R.id.year);
        priceInput = findViewById(R.id.price);
        brandInput = findViewById(R.id.brand);
        colorInput = findViewById(R.id.color);

        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                Boolean insertCar = db.insertCar(modelInput.getText().toString().trim(),
                        vinInput.getText().toString().trim(),
                        chassisInput.getText().toString().trim(),
                        motorInput.getText().toString().trim(),
                        seatsInput.getText().toString().trim(),
                        yearInput.getText().toString().trim(),
                        priceInput.getText().toString().trim(),
                        brandInput.getText().toString().trim(),
                        colorInput.getText().toString().trim());

                if (insertCar == true) {
                    Toast.makeText(AddActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}