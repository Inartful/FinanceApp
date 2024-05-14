package com.example.financeapp.feature_transaction.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.financeapp.R

sealed class CategoryType {
    sealed class Expense() : CategoryType() {
        object House : Expense()
        object Food : Expense()
        object Transport : Expense()
        object Medicine : Expense()
        object Personal : Expense()
        object Entertainment : Expense()
        object Debt : Expense()
        object Insurance : Expense()
        object Taxes : Expense()
        object Miscellaneous : Expense()
    }

    sealed class Income() : CategoryType() {
        object Salary : Income()
        object Investment : Income()
    }
}

@Composable
public fun getCategoryName(category: CategoryType): String {
    return when (category) {
        is CategoryType.Expense.House -> stringResource(id = R.string.house)
        is CategoryType.Expense.Food -> stringResource(R.string.food)
        is CategoryType.Expense.Transport -> stringResource(R.string.transport)
        is CategoryType.Expense.Medicine -> stringResource(R.string.medicine)
        is CategoryType.Expense.Personal -> stringResource(R.string.personal)
        is CategoryType.Expense.Entertainment -> stringResource(R.string.entertainment)
        is CategoryType.Expense.Debt -> stringResource(R.string.debt)
        is CategoryType.Expense.Insurance -> stringResource(R.string.insurance)
        is CategoryType.Expense.Taxes -> stringResource(R.string.taxes)
        is CategoryType.Expense.Miscellaneous -> stringResource(R.string.miscellaneous)
        is CategoryType.Income.Salary -> stringResource(R.string.salary)
        is CategoryType.Income.Investment -> stringResource(R.string.investment)
    }
}