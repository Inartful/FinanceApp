package com.example.financeapp.feature_transaction.presentation.add_transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionViewModel

@Composable
fun EnterName(
    viewModel: AddTransactionViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    TextField(
        value = state.value.name,
        onValueChange = {
            viewModel.onEvent(AddTransactionEvents.ChangeName(it))
        },
        placeholder = {
            if (state.value.name.isBlank()) {
                Text(text = stringResource(id = R.string.enter_name),
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
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        textStyle = TextStyle(
            fontSize = 20.sp
        )
    )
}