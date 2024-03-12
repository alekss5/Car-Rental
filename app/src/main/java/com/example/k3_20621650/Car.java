package com.example.k3_20621650;
import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String brand;
    private String registrationNumber;
    private String status;
    private String ownerName;

    public Car() {
        // Default constructor required by Parcelable
    }

    protected Car(Parcel in) {
        brand = in.readString();
        registrationNumber = in.readString();
        status = in.readString();
        ownerName = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(registrationNumber);
        dest.writeString(status);
        dest.writeString(ownerName);
    }
}
