package com.example.contact_simran_c0788127_android.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

@Insert
void insertContact(ContactRoom contact);

@Query("DELETE FROM contact")
void deleteAllContacts();

@Query("DELETE FROM contact where id = :id")
int deleteContact(int id);

@Query("UPDATE contact SET fName= :fName, lName = :lName, email = :email, phone = :phone, address = :address WHERE id = :id")
int updateContact(int id, String fName, String lName, String email, String phone, String address);

@Query("SELECT * FROM contact ORDER BY fName")
List<ContactRoom> getAllContacts();
}
