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

fun checkHttpCodes(context: Context, code: Int) {
    when (code) {
        200 -> showToast(context, "Успех")
        400 -> showToast(context, "Неверный запрос, неверно заданы параметры")
        401 -> showToast(context, "Неавторизованный пользователь")
        403 -> showToast(context, "Доступ запрещен или неверный токен")
        404 -> showToast(context, "Метод или данные не найдены")
        409 -> showToast(context, "Конфликт, дубликат")
        429 -> showToast(context, "Превышен лимит запросов")
        500 -> showToast(context, "Внутренняя ошибка сервера")
    }
}