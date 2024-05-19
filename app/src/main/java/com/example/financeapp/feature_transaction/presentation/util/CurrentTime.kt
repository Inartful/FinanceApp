package com.example.financeapp.feature_transaction.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.financeapp.R
import java.time.LocalDateTime
import java.time.Month

@Composable
fun getCurrentDate():String {
    var date = "${LocalDateTime.now().dayOfMonth} "
    date += when(LocalDateTime.now().month) {
        Month.JANUARY -> stringResource(R.string.january)
        Month.FEBRUARY -> stringResource(R.string.february)
        Month.MARCH -> stringResource(R.string.march)
        Month.APRIL -> stringResource(R.string.april)
        Month.MAY -> stringResource(R.string.may)
        Month.JUNE -> stringResource(R.string.june)
        Month.JULY -> stringResource(R.string.july)
        Month.AUGUST -> stringResource(R.string.august)
        Month.SEPTEMBER -> stringResource(R.string.september)
        Month.OCTOBER -> stringResource(R.string.october)
        Month.NOVEMBER -> stringResource(R.string.november)
        Month.DECEMBER -> stringResource(R.string.december)
    }
    return date
}