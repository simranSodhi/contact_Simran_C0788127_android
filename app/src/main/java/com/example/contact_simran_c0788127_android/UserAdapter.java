package com.example.contact_simran_c0788127_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contact_simran_c0788127_android.model.Contact;
import com.example.contact_simran_c0788127_android.room.ContactRoom;
import com.example.contact_simran_c0788127_android.room.ContactRoomDb;
import com.example.contact_simran_c0788127_android.util.DatabaseHelper;

import java.util.List;

public class UserAdapter extends ArrayAdapter {

    Context context;
    int layoutRes;
    List<ContactRoom> contactList;

    ContactRoomDb contactRoomDb;
    private static final String TAG = "UserAdapter";


   public UserAdapter(@NonNull Context context, int resource, List<ContactRoom> contactList) {
       super(context, resource, contactList);
       this.contactList = contactList;
       this.context = context;
       this.layoutRes = resource;
       contactRoomDb = ContactRoomDb.getInstance(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);

        TextView firstTV = v.findViewById(R.id.tv_first);
        TextView lastTV = v.findViewById(R.id.tv_last);
        TextView emailTV = v.findViewById(R.id.tv_Email);
        TextView phoneTV = v.findViewById(R.id.tv_Phone);
        TextView addressTV = v.findViewById(R.id.tv_Address);

        final ContactRoom contact = contactList.get(position);
        firstTV.setText(contact.getFName());
        lastTV.setText(contact.getLName());
        emailTV.setText(contact.getEmail());
        phoneTV.setText(contact.getPhone());
        addressTV.setText(contact.getAddress());

        v.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(contact);
            }

            private void updateContact(final ContactRoom contact) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.dialog_update_contact, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText etFirst = view.findViewById(R.id.et_firstname);
                final EditText etLast = view.findViewById(R.id.et_lastname);
                final EditText etEmail = view.findViewById(R.id.et_email);
                final EditText etPhone = view.findViewById(R.id.et_phone);
                final EditText etAddress = view.findViewById(R.id.et_address);

                etFirst.setText(contact.getFName());
                etLast.setText(contact.getLName());
                etEmail.setText(contact.getEmail());
                etPhone.setText(contact.getPhone());
                etAddress.setText(contact.getAddress());


                view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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



                        // Room
                        contactRoomDb.contactDao().updateContact(contact.getId(),
                                fName, lName,email,phone,address);
                        loadContacts();
                        alertDialog.dismiss();
                    }
                });

            }

        });

          v.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) { deleteContact(contact); }

              private void deleteContact(ContactRoom contact) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(context);
                  builder.setTitle("Are you sure?");
                  builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                            // Room
                           contactRoomDb.contactDao().deleteContact(contact.getId());
                          loadContacts();
                      }
                  });
                  builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(context, "The contact (" + contact.getFName() + ") is not deleted", Toast.LENGTH_SHORT).show();
                      }
                  });
                  AlertDialog alertDialog = builder.create();
                  alertDialog.show();
              }
          });

        Log.d(TAG, "getView: " + getCount());
          return  v;
    }

    @Override
    public int getCount() { return contactList.size(); }

    private void loadContacts() {

        contactList = contactRoomDb.contactDao().getAllContacts();
        notifyDataSetChanged();
    }
}