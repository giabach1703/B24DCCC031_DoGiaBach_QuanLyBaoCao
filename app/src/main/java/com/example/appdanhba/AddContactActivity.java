package com.example.appdanhba;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddContactActivity extends BaseActivity {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtNote;
    private Spinner spGroup;
    private RadioGroup rgGender;
    private CheckBox cbFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLayout(R.layout.activity_add_contact);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtNote = findViewById(R.id.edtNote);
        spGroup = findViewById(R.id.spGroup);
        rgGender = findViewById(R.id.rgGender);
        cbFavorite = findViewById(R.id.cbFavorite);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        String[] groups = {
                "Gia đình",
                "Bạn bè",
                "Công việc",
                "Khác"
        };

        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                groups
        );

        groupAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spGroup.setAdapter(groupAdapter);

        btnSave.setOnClickListener(v -> saveContact());
        btnBack.setOnClickListener(v -> finish());
    }

    private void saveContact() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String note = edtNote.getText().toString().trim();

        edtName.setError(null);
        edtPhone.setError(null);

        if (name.isEmpty()) {
            edtName.setError("Họ tên không được để trống");
            edtName.requestFocus();
            return;
        }

        if (!ContactRepository.isValidPhone(phone)) {
            edtPhone.setError(
                    "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0"
            );
            edtPhone.requestFocus();
            return;
        }

        if (ContactRepository.phoneExists(phone)) {
            edtPhone.setError("Số điện thoại đã tồn tại trong danh bạ");
            edtPhone.requestFocus();
            return;
        }

        int selectedGenderId = rgGender.getCheckedRadioButtonId();

        if (selectedGenderId == -1) {
            Toast.makeText(
                    this,
                    "Vui lòng chọn giới tính",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        RadioButton selectedGender = findViewById(selectedGenderId);

        String group = spGroup.getSelectedItem().toString();
        String gender = selectedGender.getText().toString();
        boolean favorite = cbFavorite.isChecked();

        Contact contact = new Contact(
                name,
                phone,
                group,
                gender,
                favorite,
                note
        );

        boolean added = ContactRepository.addContact(contact);

        if (!added) {
            edtPhone.setError("Số điện thoại đã tồn tại trong danh bạ");
            return;
        }

        Toast.makeText(
                this,
                "Đã thêm liên hệ thành công",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }
}