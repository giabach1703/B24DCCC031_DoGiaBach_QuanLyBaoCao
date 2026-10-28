package com.example.appdanhba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    private EditText edtUsername;
    private EditText edtPassword;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLayout(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvError = findViewById(R.id.tvError);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        edtUsername.setError(null);
        edtPassword.setError(null);
        tvError.setVisibility(View.GONE);

        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                edtUsername.setError("Vui lòng nhập tên đăng nhập");
            }

            if (password.isEmpty()) {
                edtPassword.setError("Vui lòng nhập mật khẩu");
            }

            tvError.setText("Không được bỏ trống tên đăng nhập hoặc mật khẩu");
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            // Không quay lại màn hình đăng nhập khi nhấn Back.
            finish();
        } else {
            tvError.setText("Sai tên đăng nhập hoặc mật khẩu");
            tvError.setVisibility(View.VISIBLE);
        }
    }
}