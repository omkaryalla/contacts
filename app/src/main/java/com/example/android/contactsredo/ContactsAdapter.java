package com.example.android.contactsredo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<ContactList> {
    public ContactsAdapter(@NonNull Context context, @NonNull List<ContactList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);

    ContactList currentItem=getItem(position);
    TextView text1= convertView.findViewById(R.id.text1);
    text1.setText(currentItem.getName());

    TextView text2=convertView.findViewById(R.id.text2);
    text2.setText(currentItem.getNumber());

    return convertView;
    }
}
