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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.ui.theme.LittleGrey

@Composable
fun TransactionsList(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier,
    historyOnClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 10.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
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
                    text = "Latest transactions",
                    fontSize = 15.sp
                )
                Button(
                    modifier = Modifier.height(30.dp),
                    onClick = historyOnClick,
                    colors = ButtonColors(
                        containerColor = LittleGrey,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = LittleGrey,
                        disabledContainerColor = LittleGrey
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "See all",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            transactions.forEach { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }

}