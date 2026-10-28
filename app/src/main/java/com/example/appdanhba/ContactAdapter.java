package com.example.appdanhba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.inflater = LayoutInflater.from(context);
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.item_contact,
                    parent,
                    false
            );

            holder = new ViewHolder();
            holder.avatar = convertView.findViewById(R.id.tvAvatar);
            holder.name = convertView.findViewById(R.id.tvContactName);
            holder.phone = convertView.findViewById(R.id.tvContactPhone);
            holder.group = convertView.findViewById(R.id.tvContactGroup);
            holder.star = convertView.findViewById(R.id.tvStar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = getItem(position);

        holder.avatar.setText(contact.getInitial());
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.group.setText(contact.getGroup());
        holder.star.setText(contact.isFavorite() ? "★" : "");

        return convertView;
    }

    private static class ViewHolder {
        TextView avatar;
        TextView name;
        TextView phone;
        TextView group;
        TextView star;
    }
}