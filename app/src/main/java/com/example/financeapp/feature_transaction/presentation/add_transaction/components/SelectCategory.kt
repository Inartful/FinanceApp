package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { viewModel.onEvent(AddTransactionEvents.ViewCategory) }
    ) {
        Text(
            text = "Category:",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = getCategoryName(state.value.category),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    if (state.value.categoryVisible) {
        Column(
            modifier = Modifier
                .selectableGroup()
        ) {
            if (state.value.type is TransactionType.Expense) {
                categoriesExpenseList.onEach {category ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state.value.category == category,
                            onClick = { viewModel.onEvent(
                                AddTransactionEvents.ChangeCategory(category))},
                            modifier = Modifier.padding(end = 8.dp),
                            colors = RadioButtonColors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.tertiary,
                                disabledSelectedColor = Color.Transparent,
                                disabledUnselectedColor = Color.Transparent
                            )
                        )
                        Text(text = getCategoryName(category),
                            fontSize = 20.sp)
                    }
                }
            }
            if (state.value.type is TransactionType.Income) {
                categoriesIncomeList.onEach {category ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state.value.category == category,
                            onClick = { viewModel.onEvent(
                                AddTransactionEvents.ChangeCategory(category))},
                            modifier = Modifier.padding(end = 8.dp),
                            colors = RadioButtonColors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.tertiary,
                                disabledSelectedColor = Color.Transparent,
                                disabledUnselectedColor = Color.Transparent
                            )
                        )
                        Text(text = getCategoryName(category),
                            fontSize = 20.sp)
                    }
                }
            }
        }
    }
}