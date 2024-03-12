package com.example.k3_20621650;
// CarDetailsActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        Intent intent = getIntent();
        if (intent != null) {
            Car car = intent.getParcelableExtra("selectedCar");
            if (car != null) {
                displayCarDetails(car);
            }
        }
    }

    private void displayCarDetails(Car car) {
        TextView brandTextView = findViewById(R.id.brandView);
        TextView regNumberTextView = findViewById(R.id.RnumberV);
        TextView statusTextView = findViewById(R.id.statusV);
        TextView ownerTextView = findViewById(R.id.ownerV);

        brandTextView.setText("brand: " + car.getBrand());
        regNumberTextView.setText("Rnumber: " + car.getRegistrationNumber());
        statusTextView.setText("status: " + car.getStatus());
        ownerTextView.setText("owner: " + car.getOwnerName());
    }
}
