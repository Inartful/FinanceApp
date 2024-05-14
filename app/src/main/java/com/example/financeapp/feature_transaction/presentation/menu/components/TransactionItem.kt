package com.example.financeapp.feature_transaction.presentation.menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.util.CategoryType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import com.example.financeapp.feature_transaction.domain.util.getCategoryName
import java.time.LocalDateTime
import java.util.Locale

@Composable
fun TransactionItem(
    transaction: Transaction,
    transactionOnClick: (transactionId: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .clickable { transactionOnClick(transaction.id!!) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(8.dp),
            painter = when(transaction.category) {
                CategoryType.Expense.Debt -> painterResource(R.drawable.wallet_svgrepo_com)
                CategoryType.Expense.Entertainment -> painterResource(R.drawable.birthday_cake_celebration_gift_party_pastry_svgrepo_com)
                CategoryType.Expense.Food -> painterResource(R.drawable.fork_and_knife_combination_svgrepo_com)
                CategoryType.Expense.House -> painterResource(R.drawable.baseline_house_24)
                CategoryType.Expense.Insurance -> painterResource(R.drawable.shield_check_svgrepo_com)
                CategoryType.Expense.Medicine -> painterResource(R.drawable.medicine_capsule_svgrepo_com)
                CategoryType.Expense.Miscellaneous -> painterResource(R.drawable.three_dots_svgrepo_com)
                CategoryType.Expense.Personal -> painterResource(R.drawable.baseline_person_24)
                CategoryType.Expense.Taxes -> painterResource(R.drawable.taxes_round_svgrepo_com)
                CategoryType.Expense.Transport -> painterResource(R.drawable.baseline_directions_car_24)
                CategoryType.Income.Investment -> painterResource(R.drawable.investment_portfolio_money_investment_cash_payment_svgrepo_com)
                CategoryType.Income.Salary -> painterResource(R.drawable.money_svgrepo_com)
            },
            contentDescription = transaction.name,
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text =
                if (transaction.name.isNullOrBlank()) {
                    getCategoryName(transaction.category)
                }
                else {
                    transaction.name
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                color = MaterialTheme.colorScheme.secondary,
                text =
                if (transaction.dateTime.dayOfMonth == LocalDateTime.now().dayOfMonth
                        && transaction.dateTime.month == LocalDateTime.now().month) {
                    stringResource(R.string.today)
                    } else if (transaction.dateTime.dayOfMonth == (LocalDateTime.now().dayOfMonth-1)
                        && transaction.dateTime.month == LocalDateTime.now().month) {
                    stringResource(R.string.yesterday)
                    } else {
                        "${transaction.dateTime.dayOfMonth}" +
                                " ${transaction.dateTime.month}".lowercase(Locale.ROOT)
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
                fontSize = 25.sp,

                fontWeight = FontWeight.Normal
            )
        }
    }
}

//@Preview
//@Composable
//fun TransactionItemPreview() {
//    FinanceAppTheme {
//        TransactionItem(transaction = Transaction(
//            id = 1,
//            LocalDateTime.now(),
//            type = TransactionType.Income,
//            amount = 150000,
//            accountId = 1,
//            category = CategoryType.Income.Salary,
//            name = "Communistic status online"
//        ),
//            transactionOnClick = {})
//    }
//}