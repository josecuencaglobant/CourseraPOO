package com.example.sharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharingapp.finalexam.Contact;
import com.example.sharingapp.finalexam.ContactAdapter;
import com.example.sharingapp.finalexam.ContactList;

public class ContactsActivity extends AppCompatActivity {

    private ContactList contact_list;
    private ContactList active_borrowers_list;
    private ItemList itemList;
    private ListView my_contacts;
    private ArrayAdapter<Contact> adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        context = this;
        contact_list = new ContactList();
        my_contacts = findViewById(R.id.my_contacts);
    }



    @Override
    protected void onStart() {
        super.onStart();
        contact_list.loadContacts(context);
        adapter = new ContactAdapter(context, contact_list.getContacts());
        my_contacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        my_contacts.setOnItemLongClickListener((parent, view, position, id) -> {
            Contact contact = adapter.getItem(position);
            int meta_pos = contact_list.getIndex(contact);
            if (meta_pos >= 0) {
                Intent edit = new Intent(context, EditContactActivity.class);
                edit.putExtra("position", meta_pos);
                startActivity(edit);
            }
            return true;
        });

    }

    public void addContactActivity(View view){
        startActivity(
                new Intent(this, AddContactActivity.class)
        );
    }

}