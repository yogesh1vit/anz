package com.anz.addressbook.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookPersist {

    private List<AddressBook> addressBooks;

    public AddressBookPersist() {
        addressBooks = new ArrayList<AddressBook>();
        addressBooks = readAddressBooks(); // reads from previous runs
    }

    public void addAddressBook(AddressBook addressbook) {
        if (!addressBooks.contains(addressbook)) {
            addressBooks.add(addressbook);
            storeAddressBooks(addressBooks);
        }

    }

    public void removeAddressBook(AddressBook addressbook) {
        if (addressBooks.contains(addressbook)) {
            addressBooks.remove(addressbook);
            storeAddressBooks(addressBooks);
        }

    }

    public List<AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public void setAddressBooks(List<AddressBook> addressBooks) {
        this.addressBooks = addressBooks;
        storeAddressBooks(addressBooks);

    }

    public void removeAllAddressBooks() {
        addressBooks.clear();
        storeAddressBooks(addressBooks);

    }

    public void storeAddressBooks(List<AddressBook> addressBooks) {
        try {
            FileOutputStream fos = new FileOutputStream("AddressBooks.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(addressBooks);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<AddressBook> readAddressBooks() {
        List<AddressBook> addressBooks = new ArrayList<AddressBook>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    "AddressBooks.txt"));
            if (ois.readObject() != null) {
                addressBooks = (List<AddressBook>) ois.readObject();
            }
            ois.close();
        } catch (EOFException ex) {
            System.out.println();
        } catch (FileNotFoundException ex) {
            System.out.println("No address books stored");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return addressBooks;
    }


}
