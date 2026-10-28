package com.example.appdanhba;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final String phone;
    private final String group;
    private final String gender;
    private final String note;
    private boolean favorite;

    public Contact(String name, String phone, String group,
                   String gender, boolean favorite, String note) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.group = group;
        this.gender = gender;
        this.favorite = favorite;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }

    public String getGender() {
        return gender;
    }

    public String getNote() {
        return note;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getInitial() {
        String[] words = name.trim().split("\\s+");
        String lastWord = words[words.length - 1];

        return lastWord.substring(0, 1)
                .toUpperCase(Locale.getDefault());
    }
}