package com.example.financeapp.feature_transaction.presentation.menu.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import com.example.financeapp.ui.theme.FinanceAppTheme
import java.time.LocalDateTime

@Composable
fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .fillMaxHeight()
                .width(70.dp)
                .padding(10.dp),
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Some name",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                color = MaterialTheme.colorScheme.secondary,
                text =
                if (transaction.dateTime.dayOfMonth == LocalDateTime.now().dayOfMonth
                        && transaction.dateTime.month == LocalDateTime.now().month) {
                        "Today"
                    } else if (transaction.dateTime.dayOfMonth == (LocalDateTime.now().dayOfMonth-1)
                        && transaction.dateTime.month == LocalDateTime.now().month) {
                        "Yesterday"
                    } else {
                        "${transaction.dateTime.dayOfMonth}" +
                                " ${transaction.dateTime.month}".toLowerCase()
                    },
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text =
                if (transaction.type == TransactionType.Income) {
                    "+ ${transaction.amount}₸"
                } else {
                    "- ${transaction.amount}₸"
                },
                fontSize = 25.sp
            )
        }
    }
}

@Preview
@Composable
fun TransactionItemPreview() {
    FinanceAppTheme {
        TransactionItem(transaction = Transaction(
            id = 1,
            LocalDateTime.now(),
            type = TransactionType.Income,
            amount = 15,
            accountId = 1
        ))
    }
}

//Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(16.dp))
//            .shadow(8.dp, spotColor = Color.Red)
//            .background(MaterialTheme.colorScheme.primary)
//            .padding(10.dp)
//    ) {
//        Column {
//            Text(
//                text = "Date: ${
//                    if (transaction.dateTime.dayOfMonth == LocalDateTime.now().dayOfMonth
//                        && transaction.dateTime.month == LocalDateTime.now().month) {
//                        "Today"
//                    } else if (transaction.dateTime.dayOfMonth == (LocalDateTime.now().dayOfMonth-1)
//                        && transaction.dateTime.month == LocalDateTime.now().month) {
//                        "Yesterday"
//                    } else {
//                        "${transaction.dateTime.dayOfMonth}" +
//                                " ${transaction.dateTime.month}".toLowerCase()
//                    }
//                }",
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Type: ${
//                    if (transaction.type == TransactionType.Income) {
//                        "Income"
//                    } else {
//                        "Expenses"
//                    }
//                }",
//                style = MaterialTheme.typography.bodyMedium,
//                fontSize = 14.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Amount: ${transaction.amount}",
//                style = MaterialTheme.typography.bodyMedium,
//                fontSize = 14.sp
//            )
//        }
//    }