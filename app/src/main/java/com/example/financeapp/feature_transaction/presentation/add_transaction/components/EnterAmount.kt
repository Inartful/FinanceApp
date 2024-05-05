package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionViewModel

@Composable
fun EnterAmount(
    viewModel: AddTransactionViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value =
        if(state.value.amount <= 0) { "" }
        else { state.value.amount.toString() },
        onValueChange = {
            viewModel.onEvent(AddTransactionEvents.ChangeAmount(it.toInt()))
        },
        placeholder = {
            if (state.value.amount <= 0) {
                Text(text = "Enter amount",
                    fontSize = 20.sp)
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        textStyle = TextStyle(
            fontSize = 20.sp
        )
    )
}