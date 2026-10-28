package com.example.appdanhba;

import java.util.ArrayList;

public class ContactRepository {

    private static final ArrayList<Contact> contacts = new ArrayList<>();

    static {
        contacts.add(new Contact(
                "Nguyễn Văn An",
                "0901234567",
                "Bạn bè",
                "Nam",
                false,
                ""
        ));

        contacts.add(new Contact(
                "Trần Thị Bình",
                "0912345678",
                "Gia đình",
                "Nữ",
                true,
                ""
        ));

        contacts.add(new Contact(
                "Lê Minh Châu",
                "0987654321",
                "Công việc",
                "Nam",
                false,
                ""
        ));
    }

    private ContactRepository() {
    }

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }

    public static Contact findById(String id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }

        return null;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("0[0-9]{9}");
    }

    public static boolean phoneExists(String phone) {
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }

        return false;
    }

    public static boolean addContact(Contact contact) {
        if (phoneExists(contact.getPhone())) {
            return false;
        }

        contacts.add(contact);
        return true;
    }

    public static void deleteContact(String id) {
        Contact contact = findById(id);

        if (contact != null) {
            contacts.remove(contact);
        }
    }

    public static int countFavorites() {
        int count = 0;

        for (Contact contact : contacts) {
            if (contact.isFavorite()) {
                count++;
            }
        }

        return count;
    }
}