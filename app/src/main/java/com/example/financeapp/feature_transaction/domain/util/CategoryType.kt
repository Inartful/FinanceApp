package com.example.financeapp.feature_transaction.domain.util

sealed class CategoryType {
    sealed class Expense(): CategoryType() {
        object House:Expense()
        object Food:Expense()
        object Transport:Expense()
        object Medicine:Expense()
        object Personal:Expense()
        object Entertainment:Expense()
        object Debt:Expense()
        object Insurance:Expense()
        object Taxes:Expense()
        object Miscellaneous:Expense()
    }
    sealed class Income(): CategoryType() {
        object Salary:Income()
        object Investment:Income()
    }
}