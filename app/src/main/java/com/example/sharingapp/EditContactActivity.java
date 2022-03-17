package com.example.sharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharingapp.finalexam.Contact;
import com.example.sharingapp.finalexam.ContactList;

public class EditContactActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private ContactList contact_list;
    private Contact contact;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = this;
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        contact_list = new ContactList();
        contact_list.loadContacts(context);

        int pos = getIntent().getIntExtra("position", 0);
        contact = contact_list.getContact(pos);

        username.setText(contact.getUsername());
        email.setText(contact.getEmail());
    }

    public void saveContact(View view){
        String usernameContact = username.getText().toString();
        String emailContact = email.getText().toString();
        if( usernameContact.equals(contact.getUsername()) ||
                contact_list.isUserNameAvailable(usernameContact)){
            int index = contact_list.getIndex(contact);
            contact.setUsername(usernameContact);
            contact.setEmail(emailContact);
            contact_list.getContacts().set(index,contact);
            contact_list.saveContacts(context);
            finish();
        }else{
            Toast.makeText(context,"Change UserName",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteContact(View view){
        contact_list.deleteContact(contact);
        contact_list.saveContacts(context);
        finish();
    }

}