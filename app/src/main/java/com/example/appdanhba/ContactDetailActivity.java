package com.example.appdanhba;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class ContactDetailActivity extends BaseActivity {

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLayout(R.layout.activity_contact_detail);

        Contact receivedContact =
                (Contact) getIntent().getSerializableExtra("contact");

        if (receivedContact != null) {
            // Intent truyền một bản sao.
            // Tìm đối tượng gốc để cập nhật đúng ArrayList.
            contact = ContactRepository.findById(
                    receivedContact.getId()
            );
        }

        if (contact == null) {
            Toast.makeText(
                    this,
                    "Liên hệ không còn tồn tại. Vui lòng chọn lại.",
                    Toast.LENGTH_LONG
            ).show();

            finish();
            return;
        }

        TextView tvAvatar = findViewById(R.id.tvDetailAvatar);
        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvPhone = findViewById(R.id.tvDetailPhone);
        TextView tvGroup = findViewById(R.id.tvDetailGroup);
        TextView tvGender = findViewById(R.id.tvDetailGender);
        TextView tvNote = findViewById(R.id.tvDetailNote);
        CheckBox cbFavorite = findViewById(R.id.cbDetailFavorite);

        Button btnCall = findViewById(R.id.btnCall);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        tvAvatar.setText(contact.getInitial());
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());
        tvGroup.setText("Nhóm: " + contact.getGroup());
        tvGender.setText("Giới tính: " + contact.getGender());

        if (contact.getNote().isEmpty()) {
            tvNote.setText("Chưa có ghi chú");
        } else {
            tvNote.setText(contact.getNote());
        }

        cbFavorite.setChecked(contact.isFavorite());

        cbFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            contact.setFavorite(isChecked);
        });

        btnCall.setOnClickListener(v -> dialPhone());
        btnBack.setOnClickListener(v -> finish());
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    private void dialPhone() {
        Intent intent = new Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + contact.getPhone())
        );

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    this,
                    "Thiết bị không có ứng dụng quay số",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa liên hệ")
                .setMessage(
                        "Bạn có chắc muốn xóa "
                                + contact.getName()
                                + "?"
                )
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Xóa", (dialog, which) -> {
                    ContactRepository.deleteContact(
                            contact.getId()
                    );

                    Toast.makeText(
                            this,
                            "Đã xóa liên hệ",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();
                })
                .show();
    }
}