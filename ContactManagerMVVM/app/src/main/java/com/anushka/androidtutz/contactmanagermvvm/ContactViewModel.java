package com.anushka.androidtutz.contactmanagermvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.anushka.androidtutz.contactmanagermvvm.db.entity.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);

        contactRepository = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContacts(){
        return contactRepository.getContactsLiveData();
    }

    public void createContact(String name, String email){
        contactRepository.createContact(name, email);
    }

    public void updateContact(Contact contact){
        contactRepository.updateContact(contact);
    }

    public void deleteContact(Contact contact){
        contactRepository.deleteContact(contact);
    }

    public void clear(){
        contactRepository.clear();
    }
}
