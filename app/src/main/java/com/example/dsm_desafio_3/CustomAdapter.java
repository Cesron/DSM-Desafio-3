package com.example.dsm_desafio_3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, model, vin, chassis, motor, seats, year, price, brand, color;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList model, ArrayList vin,
                  ArrayList chassis, ArrayList motor, ArrayList seats, ArrayList year, ArrayList price,
                  ArrayList brand, ArrayList color) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.model = model;
        this.vin = vin;
        this.chassis = chassis;
        this.motor = motor;
        this.seats = seats;
        this.year = year;
        this.price = price;
        this.brand = brand;
        this.color = color;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new CustomAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.model.setText(String.valueOf(model.get(position)));
        holder.vin.setText(String.valueOf(vin.get(position)));
        holder.chassis.setText(String.valueOf(chassis.get(position)));
        holder.motor.setText(String.valueOf(motor.get(position)));
        holder.seats.setText(String.valueOf(seats.get(position)));
        holder.year.setText(String.valueOf(year.get(position)));
        holder.price.setText(String.valueOf(price.get(position)));
        holder.brand.setText(String.valueOf(brand.get(position)));
        holder.color.setText(String.valueOf(color.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("model", String.valueOf(model.get(position)));
                intent.putExtra("vin", String.valueOf(vin.get(position)));
                intent.putExtra("chassis", String.valueOf(chassis.get(position)));
                intent.putExtra("motor", String.valueOf(motor.get(position)));
                intent.putExtra("seats", String.valueOf(seats.get(position)));
                intent.putExtra("year", String.valueOf(year.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("brand", String.valueOf(brand.get(position)));
                intent.putExtra("color", String.valueOf(color.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, model, vin, chassis, motor, seats, year, price, brand, color;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_txt);
            model = itemView.findViewById(R.id.model_txt);
            vin = itemView.findViewById(R.id.vin_txt);
            chassis = itemView.findViewById(R.id.chassis_txt);
            motor = itemView.findViewById(R.id.motor_txt);
            seats = itemView.findViewById(R.id.seats_txt);
            year = itemView.findViewById(R.id.year_txt);
            price = itemView.findViewById(R.id.price_txt);
            brand = itemView.findViewById(R.id.brand_txt);
            color = itemView.findViewById(R.id.color_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
