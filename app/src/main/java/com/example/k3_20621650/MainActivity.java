package com.example.k3_20621650;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Car> carList;
    private ArrayAdapter<Car> carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carList = generateCarList();
        carAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(carAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> showCarDetails(position));
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            editCarDetails(position);
            return true;
        });

        updateCarStatusPeriodically();
    }

    private void showCarDetails(int position) {
        Car selectedCar = carList.get(position);

        Intent intent = new Intent(this, CarDetailsActivity.class);
        intent.putExtra("selectedCar", selectedCar);
        startActivity(intent);
    }

    private void editCarDetails(int position) {
        Car selectedCar = carList.get(position);

        Intent intent = new Intent(this, EditCarDetailsActivity.class);
        intent.putExtra("selectedCar", selectedCar);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_CAR_DETAILS_REQUEST);
    }

    private static final int EDIT_CAR_DETAILS_REQUEST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_CAR_DETAILS_REQUEST && resultCode == RESULT_OK && data != null) {
            Car updatedCar = data.getParcelableExtra("updatedCar");
            int position = data.getIntExtra("position", -1);

            if (position != -1 && updatedCar != null) {
                carList.set(position, updatedCar);
                carAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateCarStatusPeriodically() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Random random = new Random();
            int positionToUpdate = random.nextInt(carList.size());
            Car car = carList.get(positionToUpdate);

            car.setStatus(random.nextBoolean() ? "Working" : "Ready");

            carAdapter.notifyDataSetChanged();

            updateCarStatusPeriodically();
        }, 2000);
    }


    private List<Car> generateCarList() {
        List<Car> cars = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Car car = new Car();
            car.setBrand("Brand" + i);
            car.setRegistrationNumber("В 425"+ i+" Х");
            car.setStatus("Ready");
            car.setOwnerName("Owner" + i);

            cars.add(car);
        }

        return cars;
    }
}