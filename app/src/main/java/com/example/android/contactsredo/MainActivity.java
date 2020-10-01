package com.example.android.contactsredo;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        getContacts();
    }

    private String[] permissions={Manifest.permission.READ_CONTACTS};

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissions, 1);
        }
    }

    public void getContacts(){
        String contactId ;
        String displayName ;
        List<ContactList> contacts = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {

                    ContactList contactsInfo = new ContactList();
                    contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contactsInfo.setContactId(contactId);
                    contactsInfo.setName(displayName);

                    Cursor phoneCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);

                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contactsInfo.setNumber(phoneNumber);
                    }

                    phoneCursor.close();

                    contacts.add(contactsInfo);
                }
            }
        }
        cursor.close();

        ContactsAdapter adapter = new ContactsAdapter(MainActivity.this, contacts);
        ListView listView=findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
