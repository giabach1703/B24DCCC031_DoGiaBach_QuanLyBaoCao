package com.example.appdanhba;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public abstract class BaseActivity extends AppCompatActivity {

    protected void showLayout(int layoutId) {
        setContentView(layoutId);

        // Phần tử ngoài cùng của mỗi giao diện có id là main.
        View root = findViewById(R.id.main);

        int originalLeft = root.getPaddingLeft();
        int originalTop = root.getPaddingTop();
        int originalRight = root.getPaddingRight();
        int originalBottom = root.getPaddingBottom();

        // Chừa khoảng trống cho thanh trạng thái, điều hướng và bàn phím.
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            | WindowInsetsCompat.Type.ime()
            );

            view.setPadding(
                    originalLeft + insets.left,
                    originalTop + insets.top,
                    originalRight + insets.right,
                    originalBottom + insets.bottom
            );

            return windowInsets;
        });

        ViewCompat.requestApplyInsets(root);
    }
}