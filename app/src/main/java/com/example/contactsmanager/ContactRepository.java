package com.example.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {

    // Availbale data sources
    private final ContactDao contactDao;


    public ContactRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);

        this.contactDao = contactDatabase.getContactDao();
    }


    // methods from DAO being executed from repo
    public void addContact(Contact contact) {

        // for executing operation in background
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // for updating ui
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contact);
            }
        });


    }

    public void deleteContact(Contact contact) {

        // for executing operation in background
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // for updating ui
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contact);
            }
        });

    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
