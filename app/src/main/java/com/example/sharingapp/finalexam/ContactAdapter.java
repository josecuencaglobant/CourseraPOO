package com.example.sharingapp.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sharingapp.Item;
import com.example.sharingapp.R;

import java.io.PipedOutputStream;
import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private LayoutInflater inflater;
    private Context context;


    public ContactAdapter(Context context, ArrayList<Contact> items){
        super(context, 0, items);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        String username = context.getResources().getString(R.string.username_hint) +": " + contact.getUsername();
        String email = context.getResources().getString(R.string.email_hint) +": " + contact.getEmail();

        if (convertView == null) {
            convertView = inflater.from(context).inflate(R.layout.contact_item, parent, false);
        }

        TextView username_tv = (TextView) convertView.findViewById(R.id.username_tv);
        username_tv.setText(username);

        TextView email_tv = (TextView) convertView.findViewById(R.id.email_tv);
        email_tv.setText(email);

        return convertView;
    }

}
