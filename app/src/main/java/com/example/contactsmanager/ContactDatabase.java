package com.example.contactsmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    //singleton pattern for database
    private static ContactDatabase contactDatabase;

    public static synchronized ContactDatabase getInstance(Context context){
        if(contactDatabase == null){
            contactDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();

        }
        return contactDatabase;
    }

}
