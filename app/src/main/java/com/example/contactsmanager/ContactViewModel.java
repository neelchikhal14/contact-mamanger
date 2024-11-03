package com.example.contactsmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

  // Imp You need to extend AndroidViewModel instead of ViewModel
  // if you want to get application context
  // see constructor
  
  private final ContactRepository contactRepository;


  // lIve Data
  private LiveData<List<Contact>> contactLiveData;

  public ContactViewModel(@NonNull Application application) {
    super(application);
    this.contactRepository = new ContactRepository(application);
  }

  public LiveData<List<Contact>> getAllContacts(){
    return contactRepository.getAllContacts();
  }

  public void addNewContact(Contact contact){
    contactRepository.addContact(contact);
  }
  public void deleteNewContact(Contact contact){
    contactRepository.deleteContact(contact);
  }
}
