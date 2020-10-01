package com.example.android.contactsredo;

public class ContactList {
    private String mContactId;
    private String mName;
    private String mNumber;

    public void setContactId(String ContactId) {
        this.mContactId = ContactId;
    }

    public String getContactId() {
        return mContactId;
    }

    public void setName(String Name) {
        this.mName = Name;
    }

    public void setNumber(String Number) {
        this.mNumber = Number;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }
}
