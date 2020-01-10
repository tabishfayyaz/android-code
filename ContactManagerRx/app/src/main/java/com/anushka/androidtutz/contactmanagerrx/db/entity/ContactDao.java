package com.anushka.androidtutz.contactmanagerrx.db.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ContactDao {

    @Insert
    public long addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Query("select * from contacts")
    public Flowable<List<Contact>> getContacts();

    @Query("select * from contacts where contact_id==:contactId")
    public Contact getContact(long contactId);
}
