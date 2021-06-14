package com.example.contact_simran_c0788127_android.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class ContactRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name="fName")
    private String fName;

    @NonNull
    @ColumnInfo(name="lName")
    private  String lName;

    @NonNull
    @ColumnInfo(name="email")
    private String email;

    @NonNull
    @ColumnInfo(name="phone")
    private  String phone;

    @NonNull
    @ColumnInfo(name="address")
    private  String address;

    public ContactRoom(@NonNull String fName, @NonNull String lName, @NonNull String email, @NonNull String phone, @NonNull String address) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getFName() {
        return fName;
    }

    @NonNull
    public String getLName() {
        return lName;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFName(@NonNull String fName) {
        this.fName = fName;
    }

    public void setLName(@NonNull String lName) {
        this.lName = lName;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }
}
