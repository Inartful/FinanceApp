package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.feature_transaction.domain.util.CategoryType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import com.example.financeapp.feature_transaction.domain.util.getCategoryName
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionViewModel

@Composable
fun SelectCategory(
    viewModel: AddTransactionViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val categoriesExpenseList = listOf(
        CategoryType.Expense.House,
        CategoryType.Expense.Food,
        CategoryType.Expense.Transport,
        CategoryType.Expense.Medicine,
        CategoryType.Expense.Personal,
        CategoryType.Expense.Entertainment,
        CategoryType.Expense.Debt,
        CategoryType.Expense.Insurance,
        CategoryType.Expense.Taxes,
        CategoryType.Expense.Miscellaneous
    )
    val categoriesIncomeList = listOf(
        CategoryType.Income.Salary,
        CategoryType.Income.Investment
    )

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Choose category",
            fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        DropdownMenu(
            expanded = false,
            onDismissRequest = { },
            modifier = Modifier.width(200.dp)
        ) {
            if (state.value.type == TransactionType.Income) {
                categoriesIncomeList.forEach { category ->
                    DropdownMenuItem(
                        text = {Text(text = getCategoryName(category))},
                        onClick = {
                            viewModel.onEvent(AddTransactionEvents.ChangeCategory(category))
                        }
                    )
                }
            } else {
                categoriesExpenseList.forEach { category ->
                    DropdownMenuItem(
                        text = {Text(text = getCategoryName(category))},
                        onClick = {
                            viewModel.onEvent(AddTransactionEvents.ChangeCategory(category))
                        }
                    )
                }
            }
        }
    }
}