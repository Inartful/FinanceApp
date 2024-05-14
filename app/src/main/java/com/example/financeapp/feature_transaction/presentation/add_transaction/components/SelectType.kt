package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionViewModel

@Composable
fun SelectType(
    viewModel: AddTransactionViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.selectableGroup()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.value.type == TransactionType.Income,
                onClick = { viewModel.onEvent(
                    AddTransactionEvents
                        .ChangeType(TransactionType.Income)) },
                modifier = Modifier.padding(end = 8.dp),
                colors = RadioButtonColors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.tertiary,
                    disabledSelectedColor = Color.Transparent,
                    disabledUnselectedColor = Color.Transparent
                )
            )
            Text(text = stringResource(R.string.income),
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = state.value.type == TransactionType.Expense,
                onClick = { viewModel.onEvent(
                    AddTransactionEvents
                        .ChangeType(TransactionType.Expense)) },
                modifier = Modifier.padding(end = 8.dp),
                colors = RadioButtonColors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.tertiary,
                    disabledSelectedColor = Color.Transparent,
                    disabledUnselectedColor = Color.Transparent
                )
            )
            Text(text = stringResource(R.string.expense),
                fontSize = 20.sp)
        }
    }
}