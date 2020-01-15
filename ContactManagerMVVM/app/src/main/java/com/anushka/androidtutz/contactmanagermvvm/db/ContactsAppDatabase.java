package com.anushka.androidtutz.contactmanagermvvm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anushka.androidtutz.contactmanagermvvm.db.entity.Contact;
import com.anushka.androidtutz.contactmanagermvvm.db.entity.ContactDao;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();
}
