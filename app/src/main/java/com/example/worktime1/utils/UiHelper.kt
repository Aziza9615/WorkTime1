package com.example.worktime1.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.worktime1.R

var toast: Toast? = null
fun showToast(context: Context, message: String) {
    if (toast != null) toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}

fun showAlertDialog(context: Context, action: () -> Unit) {
    AlertDialog.Builder(context)
        .setTitle("Звонок")
        .setMessage("Вы действительно хотите позвонить ?")
        .setPositiveButton(
            "Да"
        ) { dialog, _ ->
            action()
            dialog.dismiss()
        }
        .setNegativeButton("Нет", null)
        .show()
}