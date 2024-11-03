package com.example.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickhandler {

    Contact contact;
    Context context;
    ContactViewModel myViewModel;

    public AddNewContactClickhandler(Contact contact, Context context, ContactViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view){
        if(contact.getEmail() == null || contact.getName() == null){
            Toast.makeText(context,"Fields are null",Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(context,MainActivity.class);

//            i.putExtra("Name",contact.getName());
//            i.putExtra("Email",contact.getEmail());

            Contact c = new Contact(contact.getName(),contact.getEmail());


            myViewModel.addNewContact(c);

            context.startActivity(i);
        }
    }
}
