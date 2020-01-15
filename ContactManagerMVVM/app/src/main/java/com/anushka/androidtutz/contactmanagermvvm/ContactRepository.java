package com.anushka.androidtutz.contactmanagermvvm;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;

import com.anushka.androidtutz.contactmanagermvvm.db.ContactsAppDatabase;
import com.anushka.androidtutz.contactmanagermvvm.db.entity.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactRepository {

    private Application application;
    private ContactsAppDatabase contactsAppDatabase;
    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<Contact>> getContactsLiveData() {
        return contactsLiveData;
    }

    private MutableLiveData<List<Contact>> contactsLiveData = new MutableLiveData<>();

    public ContactRepository(Application application) {
        this.application = application;

        //all db operations are happening in background thread using rxjava
        contactsAppDatabase = Room.databaseBuilder(application.getApplicationContext(), ContactsAppDatabase.class, "ContactDBMvvm").build();

        //this subscription will execute every time there is a change in db so we don't need code to refresh recycler inside update, delete and add contact methods
        disposable.add(
                contactsAppDatabase.getContactDao().getContacts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Contact>>() {
                            @Override
                            public void accept(List<Contact> contacts) throws Exception {
                                contactsLiveData.postValue(contacts);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }));
    }

    public void createContact(final String name, final String email) {

        //operations like create, update & delete are either going to complete or not so they can be interfaced using Completable
        disposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        //0 id is not used since db will generate for us
                        long id = contactsAppDatabase.getContactDao().addContact(new Contact(0, name, email));
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        })
        );
    }

    public void updateContact(final Contact contact) {

        disposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        contactsAppDatabase.getContactDao().updateContact(contact);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        })
        );
    }

    public void deleteContact(final Contact contact) {

        disposable.add(
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        contactsAppDatabase.getContactDao().deleteContact(contact);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        })
        );
    }

    public void clear(){
        disposable.clear();
    }
}
