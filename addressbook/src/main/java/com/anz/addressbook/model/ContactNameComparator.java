package com.anz.addressbook.model;

import java.util.Comparator;

public class ContactNameComparator implements Comparator<Contact> {
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getName().compareToIgnoreCase(contact2.getName());
    }
}