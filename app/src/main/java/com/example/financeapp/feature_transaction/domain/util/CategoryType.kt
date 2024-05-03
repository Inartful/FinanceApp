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

public fun getCategoryName(category: CategoryType): String {
    return when (category) {
        is CategoryType.Expense.House -> "House"
        is CategoryType.Expense.Food -> "Food"
        is CategoryType.Expense.Transport -> "Transport"
        is CategoryType.Expense.Medicine -> "Medicine"
        is CategoryType.Expense.Personal -> "Personal"
        is CategoryType.Expense.Entertainment -> "Entertainment"
        is CategoryType.Expense.Debt -> "Debt"
        is CategoryType.Expense.Insurance -> "Insurance"
        is CategoryType.Expense.Taxes -> "Taxes"
        is CategoryType.Expense.Miscellaneous -> "Miscellaneous"
        is CategoryType.Income.Salary -> "Salary"
        is CategoryType.Income.Investment -> "Investment"
    }
}