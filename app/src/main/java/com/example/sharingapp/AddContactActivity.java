package com.example.sharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharingapp.finalexam.Contact;
import com.example.sharingapp.finalexam.ContactList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class AddContactActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private ContactList contact_list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        context = this;

        contact_list = new ContactList();
        contact_list.loadContacts(this);

    }

    public void saveContact(View view){
        Contact contact = new Contact();
        contact.setUsername( username.getText().toString() );
        contact.setEmail( email.getText().toString() );

        if(contact_list.isUserNameAvailable(contact.getUsername())){
            contact_list.addContact(contact);
            contact_list.saveContacts(context);
            finish();
        }else{
            Toast.makeText(this,"Change the UserName",Toast.LENGTH_SHORT).show();
        }

    }

}