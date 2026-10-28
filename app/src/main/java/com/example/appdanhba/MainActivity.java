package com.example.appdanhba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private TextView tvStatistics;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLayout(R.layout.activity_main);

        tvStatistics = findViewById(R.id.tvStatistics);
        ListView lvContacts = findViewById(R.id.lvContacts);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        Button btnAddContact = findViewById(R.id.btnAddContact);

        adapter = new ContactAdapter(
                this,
                ContactRepository.getContacts()
        );

        lvContacts.setAdapter(adapter);
        lvContacts.setEmptyView(tvEmpty);

        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddContactActivity.class
            );

            startActivity(intent);
        });

        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = adapter.getItem(position);

            Intent intent = new Intent(
                    MainActivity.this,
                    ContactDetailActivity.class
            );

            intent.putExtra("contact", contact);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();

            String statistics =
                    "Tổng số liên hệ: "
                            + ContactRepository.getContacts().size()
                            + "\nLiên hệ yêu thích: "
                            + ContactRepository.countFavorites();

            tvStatistics.setText(statistics);
        }
    }
}