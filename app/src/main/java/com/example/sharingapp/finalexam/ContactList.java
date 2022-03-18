package com.example.sharingapp.finalexam;

import android.content.Context;
import android.widget.Toast;

import com.example.sharingapp.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class ContactList {

    private ArrayList<Contact> contacts;
    private String FILENAME = "contacts.sav";

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> userNames = new ArrayList<>();
            for(Contact contact: getContacts()){
                userNames.add( contact.getUsername() );
            }
        return userNames;
    }

    public void addContact(Contact contact){
        getContacts().add(contact);
    }

    public void deleteContact(Contact contact){
        getContacts().remove(contact);
    }

    public Contact getContact(int index){
        return getContacts().get(index);
    }

    public int getSize(){
        return getContacts().size();
    }

    public int getIndex(Contact contact){
        int index = 0;
        for(Contact value: getContacts()){
            if(value.getUsername().equals(contact.getUsername())){
                return index;
            }
            index++;
        }
        return 0;
    }

    public boolean hasContact(Contact contact){
        return getContacts().contains(contact);
    }

    public Contact getContactByUserName(String userName){
        Contact contactResult = null;

        for(Contact contact: getContacts()){
            if(contact.getUsername().equals(userName)){
                contactResult = contact;
                break;
            }
        }

        return  contactResult;
    }

    public void loadContacts(Context context){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type contactType = new TypeToken<ArrayList<Contact>>() {}.getType();
            setContacts((ArrayList<Contact>) gson.fromJson(isr, contactType)); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            setContacts(new ArrayList<Contact>());
        } catch (IOException e) {
            setContacts(new ArrayList<Contact>());
        }

    }

    public void saveContacts(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(getContacts(), osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserNameAvailable(String username){
        for(String value: getAllUsernames()){
            if(value.equals(username)){
                return false;
            }
        }
        return true;
    }

}
