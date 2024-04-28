package com.example.financeapp.feature_transaction.domain.util

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(formatter)
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String): LocalDateTime {
        return dateTimeString.let { LocalDateTime.parse(it, formatter) }
    }

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return when (value) {
            is TransactionType.Income -> "Income"
            is TransactionType.Outcome -> "Outcome"
        }
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return when (value) {
            "Income" -> TransactionType.Income
            "Outcome" -> TransactionType.Outcome
            else -> throw IllegalArgumentException("Unknown transaction type: $value")
        }
    }

//     For currency
    @TypeConverter
    fun fromCurrencyType(value: String): CurrencyType {
        return when (value) {
            "KZT" -> CurrencyType.KZT
            "USS" -> CurrencyType.USS
            "EUR" -> CurrencyType.EUR
            "RUR" -> CurrencyType.RUR
            else -> throw IllegalArgumentException("Unknown currency type: $value")
        }
    }

    @TypeConverter
    fun toCurrencyType(currencyType: CurrencyType): String {
        return when (currencyType) {
            CurrencyType.KZT -> "KZT"
            CurrencyType.USS -> "USS"
            CurrencyType.EUR -> "EUR"
            CurrencyType.RUR -> "RUR"
        }
    }
}
