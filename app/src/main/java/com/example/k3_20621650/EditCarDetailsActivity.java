package com.example.k3_20621650;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditCarDetailsActivity extends AppCompatActivity {

    private EditText brandEditText;
    private EditText regNumberEditText;
    private EditText statusEditText;
    private EditText ownerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car_details);

        brandEditText = findViewById(R.id.brand);
        regNumberEditText = findViewById(R.id.Rnumber);
        statusEditText = findViewById(R.id.status);
        ownerEditText = findViewById(R.id.owner);

        Intent intent = getIntent();
        if (intent != null) {
            Car selectedCar = intent.getParcelableExtra("selectedCar");
            if (selectedCar != null) {
                populateFields(selectedCar);
            }
        }

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveUpdatedDetails());
    }

    private void populateFields(Car selectedCar) {
        brandEditText.setText(selectedCar.getBrand());
        regNumberEditText.setText(selectedCar.getRegistrationNumber());
        statusEditText.setText(selectedCar.getStatus());
        ownerEditText.setText(selectedCar.getOwnerName());
    }

    private void saveUpdatedDetails() {
        String updatedBrand = brandEditText.getText().toString();
        String updatedRegNumber = regNumberEditText.getText().toString();
        String updatedStatus = statusEditText.getText().toString();
        String updatedOwner = ownerEditText.getText().toString();

        if (TextUtils.isEmpty(updatedBrand) || TextUtils.isEmpty(updatedRegNumber) ||
                TextUtils.isEmpty(updatedStatus) || TextUtils.isEmpty(updatedOwner)) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Car updatedCar = new Car();
        updatedCar.setBrand(updatedBrand);
        updatedCar.setRegistrationNumber(updatedRegNumber);
        updatedCar.setStatus(updatedStatus);
        updatedCar.setOwnerName(updatedOwner);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updatedCar", updatedCar);
        resultIntent.putExtra("position", getIntent().getIntExtra("position", -1));
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
