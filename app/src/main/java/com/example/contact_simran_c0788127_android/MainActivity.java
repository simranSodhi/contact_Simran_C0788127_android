package com.example.contact_simran_c0788127_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contact_simran_c0788127_android.model.Contact;
import com.example.contact_simran_c0788127_android.room.ContactRoom;
import com.example.contact_simran_c0788127_android.room.ContactRoomDb;
import com.example.contact_simran_c0788127_android.util.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ContactRoomDb contactRoomDb;

    EditText etFirst,etLast,etEmail,etPhone,etAddress;
    Button add;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        add = findViewById(R.id.buttonAdd);
        display = findViewById(R.id.tvDisplay);

        //set objects as listeners
        add.setOnClickListener(this);
        display.setOnClickListener(this);

        // Room db
        contactRoomDb = ContactRoomDb.getInstance(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                addContact();
                break;
            case R.id.tvDisplay:
                startActivity(new Intent(this, UserActivity.class));
                break;
        }
    }

    private void addContact()
    {
        String fName = etFirst.getText().toString().trim();
        String lName = etLast.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (fName.isEmpty()) {
            etFirst.setError("this field cannot be empty");
            etFirst.requestFocus();
            return;
        }

        if (lName.isEmpty()) {
            etLast.setError("this field cannot be empty");
            etLast.requestFocus();
            return;
        }

        if (email.isEmpty()) {
                etEmail.setError("this field cannot be empty");
                etEmail.requestFocus();
                return;
            }

        if (phone.isEmpty()) {
            etPhone.setError("this field cannot be empty");
            etPhone.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            etAddress.setError("this field cannot be empty");
            etAddress.requestFocus();
            return;
        }

        // Insert into room db
        ContactRoom contact = new ContactRoom(fName,lName,email,phone,address);
        contactRoomDb.contactDao().insertContact(contact);
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        clearFields();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        clearFields();
    }

    private void clearFields() {
        etFirst.setText("");
        etLast.setText("");
        etEmail.setText("");
        etAddress.setText("");
        etPhone.setText("");
    }


}


