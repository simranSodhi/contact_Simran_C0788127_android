package com.example.contact_simran_c0788127_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.contact_simran_c0788127_android.model.Contact;
import com.example.contact_simran_c0788127_android.room.ContactRoom;
import com.example.contact_simran_c0788127_android.room.ContactRoomDb;
import com.example.contact_simran_c0788127_android.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {


    // Room db instance
    private ContactRoomDb contactRoomDb;

    List<ContactRoom> contactList;
    ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        contactListView = findViewById(R.id.lv_contacts);
        contactList = new ArrayList<>();

        // Room
        contactRoomDb = ContactRoomDb.getInstance(this);
        loadContacts();
    }

    private void loadContacts() {
        contactList = contactRoomDb.contactDao().getAllContacts();

        UserAdapter userAdapter = new UserAdapter(this, R.layout.list_layout_contact, contactList);
        contactListView.setAdapter(userAdapter);
    }
}