package org.example;

public class Main {
    public static void main(String[] args) {

        Contact contact1 = new Contact("Udeme", "Kendrick", "+814227430");
        contact1.validateFirstName();
        contact1.validateLastName();
        contact1.validatePhoneNumber();

        Contact contact2 = new Contact("Kendrick", "Udeme", "+802260864");
        contact2.validateFirstName();
        contact2.validateLastName();
        contact2.validatePhoneNumber();

        ContactManager contactManager = new ContactManager();
        contactManager.contactList.put("0", contact1);
        contactManager.contactList.put("1", contact2);
        contactManager.addContact("Anne", "Bella", "+802345678");
        System.out.println(contactManager);
        System.out.println(contactManager.getAllContacts());
        System.out.println(contactManager.contactList.get("0"));
    }
}