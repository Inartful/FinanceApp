package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
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
    addOnClick: (accountId: Int) -> Unit
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
            modifier = Modifier.weight(1f),
            text = getText(state),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .height(25.dp),
            onClick = {
                addOnClick(-1)
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.tertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary
            ),
            contentPadding = PaddingValues(2.dp)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.tertiary),
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add account",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
    if (state.value.accountVisible) {
        LazyColumn(
            modifier = Modifier
                .selectableGroup()
                .height(
                    (state.value.accounts.size * 50).dp
                )
        ) {
            items(state.value.accounts) {account ->
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
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                addOnClick(account.id ?: -1)
                            })
                }
            }
//            state.value.accounts.onEach {account ->
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    RadioButton(
//                        selected = state.value.accountId == account.id,
//                        onClick = { viewModel.onEvent(
//                            AddTransactionEvents.ChangeAccount(account.id!!))},
//                        modifier = Modifier.padding(end = 8.dp),
//                        colors = RadioButtonColors(
//                            selectedColor = MaterialTheme.colorScheme.primary,
//                            unselectedColor = MaterialTheme.colorScheme.tertiary,
//                            disabledSelectedColor = Color.Transparent,
//                            disabledUnselectedColor = Color.Transparent
//                        )
//                    )
//                    Text(text = account.name,
//                        fontSize = 20.sp)
//                }
//            }
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