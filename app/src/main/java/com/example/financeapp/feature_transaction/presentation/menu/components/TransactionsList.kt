package com.example.financeapp.feature_transaction.presentation.menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.domain.model.Transaction

@Composable
fun TransactionsList(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier,
    historyOnClick: () -> Unit,
    transactionOnClick: (transactionId: Int) -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(start = 10.dp, top = 20.dp, bottom = 2.dp, end = 20.dp)
    ) {
        Column {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    color = MaterialTheme.colorScheme.secondary,
                    text = stringResource(R.string.latest_transactions),
                    fontSize = 15.sp
                )
                Button(
                    modifier = Modifier.height(30.dp),
                    onClick = historyOnClick,
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.see_all),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            transactions.forEach { transaction ->
                TransactionItem(
                    transaction = transaction,
                    transactionOnClick = transactionOnClick
                )
            }
        }
    }

}