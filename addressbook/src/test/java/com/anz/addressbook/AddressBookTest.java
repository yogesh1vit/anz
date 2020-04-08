package com.anz.addressbook;

import com.anz.addressbook.model.AddressBook;
import com.anz.addressbook.model.AddressBookPersist;
import com.anz.addressbook.model.Contact;
import com.anz.addressbook.model.ContactNameComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class AddressBookTest {
    private Contact c1, c2, c3, c4, c5, c6, c7;
    private AddressBookPersist addressBookPersist;

    public static void main(String[] args) {
        AddressBookTest test = new AddressBookTest();
        test.setUp();
        test.sortFriendsByTheirNames();
        test.uniqueFriendsFromTwoAddressBooks();
        test.uniqueFriendsFromThreeAddressBooks();
    }

    @Before
    public void setUp() {
        addressBookPersist = new AddressBookPersist();
        createNewContacts();
    }

    @Test
    public void uniqueFriendsFromTwoAddressBooks() {
        System.out.println("========== Unique Friends from Two Address Books ==========");

        addTwoAddressBooks();

        List<AddressBook> addressBooks = addressBookPersist.getAddressBooks();
        printInput(addressBooks);

        // Get unique contacts from two AddressBooks
        Set<Contact> uniqueContacts = AddressBook.getUniqueContacts(addressBooks);

        printOutput(addressBooks, uniqueContacts);

        // The unique contacts from these two address books should be c1 and c4
        Set<Contact> expected = new HashSet<Contact>(Arrays.asList(c1, c4));

        assertTrue(uniqueContacts.equals(expected));
    }

    @Test
    public void uniqueFriendsFromThreeAddressBooks() {
        System.out.println("========== Unique Friends from Three Address Books ==========");

        addThreeAddressBooks();

        List<AddressBook> addressBooks = addressBookPersist.getAddressBooks();
        printInput(addressBooks);

        // Get unique contacts from three AddressBooks
        Set<Contact> uniqueContacts = AddressBook.getUniqueContacts(addressBooks);

        printOutput(addressBooks, uniqueContacts);

        // The unique contacts from three address books should be c1, c4 and c5, c6, c7
        Set<Contact> expected = new HashSet<Contact>(Arrays.asList(c4, c5, c6, c7));

        assertTrue(uniqueContacts.equals(expected));
    }

    @Test
    public void sortFriendsByTheirNames() {
        System.out.println("========== Display the list of friends sorted by their name ==========");

        AddressBook addressBook = new AddressBook("ab1");
        addressBook.addContact(c5);
        addressBook.addContact(c1);
        addressBook.addContact(c4);
        addressBook.addContact(c2);
        addressBook.addContact(c3);


        System.out.println("==Input==");
        System.out.println("Address Book: " + addressBook.getName());
        System.out.println("Friends:");
        for (Contact contact : addressBook.getContacts()) {
            System.out.println(contact);
        }
        System.out.println();

        Collections.sort(addressBook.getContacts(), new ContactNameComparator());
        System.out.println("==Output==");
        System.out.println("Address Book: " + addressBook.getName());
        System.out.println("Friends:");
        for (Contact contact : addressBook.getContacts()) {
            System.out.println(contact);
        }
        System.out.println("\n");

        // Sorted list
        assertTrue("Bob".equals(addressBook.getContacts().get(0).getName()));
        assertTrue("Jane".equals(addressBook.getContacts().get(1).getName()));
        assertTrue("John".equals(addressBook.getContacts().get(2).getName()));
        assertTrue("Mary".equals(addressBook.getContacts().get(3).getName()));
        assertTrue("Ruby".equals(addressBook.getContacts().get(4).getName()));

    }

    private void createNewContacts() {
        c1 = new Contact("Bob", "02 9218 5479");
        c2 = new Contact("Mary", "04 9218 5479");
        c3 = new Contact("Jane", "02 9 605 3147");
        c4 = new Contact("John", "02 605 3147");
        c5 = new Contact("Ruby", "03 9 605 3147");
        c6 = new Contact("Paul", "03 9 605 3147");
        c7 = new Contact("Zee", "03 9 605 3147");

    }

    private void addTwoAddressBooks() {
        addressBookPersist.removeAllAddressBooks();

        AddressBook ab1 = new AddressBook("ab1");
        AddressBook ab2 = new AddressBook("ab2");

        // AddContacts to the addressBooks
        ab1.addContact(c1);
        ab1.addContact(c2);
        ab1.addContact(c3);

        ab2.addContact(c2);
        ab2.addContact(c4);
        ab2.addContact(c3);

        addressBookPersist.addAddressBook(ab1);
        addressBookPersist.addAddressBook(ab2);
    }

    private void addThreeAddressBooks() {

        addressBookPersist.removeAllAddressBooks();

        AddressBook ab1 = new AddressBook("ab1");
        AddressBook ab2 = new AddressBook("ab2");
        AddressBook ab3 = new AddressBook("ab3");

        // AddContacts to the addressBooks
        ab1.addContact(c1);
        ab1.addContact(c2);
        ab1.addContact(c3);

        ab2.addContact(c2);
        ab2.addContact(c4);
        ab2.addContact(c3);

        ab3.addContact(c1);
        ab3.addContact(c5);
        ab3.addContact(c6);
        ab3.addContact(c7);

        addressBookPersist.addAddressBook(ab1);
        addressBookPersist.addAddressBook(ab2);
        addressBookPersist.addAddressBook(ab3);

    }

    private void printInput(List<AddressBook> addressBooks) {
        System.out.println("==Input==");

        for (AddressBook addressBook : addressBooks) {
            System.out.println("Address Book: " + addressBook.getName());
            System.out.println("Friends:");
            for (Contact c : addressBook.getContacts()) {
                System.out.println(c.getName());
            }
            System.out.println();

        }
    }

    private void printOutput(List<AddressBook> addressBooks, Set<Contact> uniqueContacts) {
        System.out.println("==Output==");
        System.out.print("Address Books: ");
        String names = "";
        for (AddressBook addressBook : addressBooks) {
            names += addressBook.getName() + ", ";
        }

        if (names.length() > 0) {
            System.out.println(names.substring(0, names.lastIndexOf(",")));
        }
        for (Contact c : uniqueContacts) {
            System.out.println(c.getName());
        }
        System.out.println("\n");
    }

}