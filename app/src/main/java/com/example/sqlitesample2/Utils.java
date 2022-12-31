package com.example.sqlitesample2;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
