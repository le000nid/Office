package com.example.officemanagerapp.ui

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

object FieldsValidator {

    fun nameValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "ФИО обязателен")
            return false
        }
        return true
    }

    fun phoneValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Телефон обязателен")
            return false
        }
        return true
    }

    fun dateStartValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Дата начала обязательна")
            return false
        }
        return true
    }

    fun dateEndValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Дата окончания обязательна")
            return false
        }
        return true
    }

    fun commentValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Комментарий обязателен")
            return false
        }
        return true
    }

    fun addressValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Адрес обязателен")
            return false
        }
        return true
    }

    fun floorValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Этаж обязателен")
            return false
        }
        return true
    }

    fun roomValidator(layout: TextInputLayout, value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            setErrorMessage(layout, "Кабинет обязателен")
            return false
        }
        return true
    }

    fun clearError(view: TextInputLayout) { view.error = null }

    private fun setErrorMessage(view: TextInputLayout, errorMessage: String) {
        view.error = errorMessage
    }
}