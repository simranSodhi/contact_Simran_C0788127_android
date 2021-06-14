package com.example.contact_simran_c0788127_android.model;

public class Contact {

    int id;
    String fName,lName,email,phone,address;

    public Contact(int id, String fName, String lName, String email, String phone, String address) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
