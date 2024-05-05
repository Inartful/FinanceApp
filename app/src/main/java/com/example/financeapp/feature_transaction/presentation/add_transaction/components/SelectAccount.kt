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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionState
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionViewModel

@Composable
fun SelectAccount(
    viewModel: AddTransactionViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { viewModel.onEvent(AddTransactionEvents.ViewAccount) }
    ) {
        Text(
            text = "Account:",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = getText(state),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    if (state.value.accountVisible) {
        Column(
            modifier = Modifier
                .selectableGroup()
        ) {
            state.value.accounts.onEach {account ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.value.accountId == account.id,
                        onClick = { viewModel.onEvent(
                            AddTransactionEvents.ChangeAccount(account.id!!))},
                        modifier = Modifier.padding(end = 8.dp),
                        colors = RadioButtonColors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary,
                            disabledSelectedColor = Color.Transparent,
                            disabledUnselectedColor = Color.Transparent
                        )
                    )
                    Text(text = account.name,
                        fontSize = 20.sp)
                }
            }
        }
    }

}

private fun getText(state: State<AddTransactionState>): String {
    var text = ""
    if (state.value.accountId > 0) {
        state.value.accounts.forEach {account ->
            if (account.id == state.value.accountId) text = account.name
        }
    }
    return text
}