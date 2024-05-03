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
            is TransactionType.Expense -> "Outcome"
        }
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return when (value) {
            "Income" -> TransactionType.Income
            "Outcome" -> TransactionType.Expense
            else -> throw IllegalArgumentException("Unknown transaction type: $value")
        }
    }

//     For currency
//    @TypeConverter
//    fun fromCurrencyType(value: String): CurrencyType {
//        return when (value) {
//            "KZT" -> CurrencyType.KZT
//            "USS" -> CurrencyType.USS
//            "EUR" -> CurrencyType.EUR
//            "RUR" -> CurrencyType.RUR
//            else -> throw IllegalArgumentException("Unknown currency type: $value")
//        }
//    }
//
//    @TypeConverter
//    fun toCurrencyType(currencyType: CurrencyType): String {
//        return when (currencyType) {
//            CurrencyType.KZT -> "KZT"
//            CurrencyType.USS -> "USS"
//            CurrencyType.EUR -> "EUR"
//            CurrencyType.RUR -> "RUR"
//        }
//    }

    @TypeConverter
    fun fromCategoryType(categoryType: CategoryType): String {
        return when (categoryType) {
            is CategoryType.Expense.House -> "House"
            is CategoryType.Expense.Food -> "Food"
            is CategoryType.Expense.Transport -> "Transport"
            is CategoryType.Expense.Medicine -> "Medicine"
            is CategoryType.Expense.Personal -> "Personal"
            is CategoryType.Expense.Entertainment -> "Entertainment"
            is CategoryType.Expense.Debt -> "Debt"
            is CategoryType.Expense.Insurance -> "Insurance"
            is CategoryType.Expense.Taxes -> "Taxes"
            is CategoryType.Expense.Miscellaneous -> "Miscellaneous"
            is CategoryType.Income.Salary -> "Salary"
            is CategoryType.Income.Investment -> "Investment"
        }
    }

    @TypeConverter
    fun toCategoryType(value: String): CategoryType {
        return when (value) {
            "House" -> CategoryType.Expense.House
            "Food" -> CategoryType.Expense.Food
            "Transport" -> CategoryType.Expense.Transport
            "Medicine" -> CategoryType.Expense.Medicine
            "Personal" -> CategoryType.Expense.Personal
            "Entertainment" -> CategoryType.Expense.Entertainment
            "Debt" -> CategoryType.Expense.Debt
            "Insurance" -> CategoryType.Expense.Insurance
            "Taxes" -> CategoryType.Expense.Taxes
            "Miscellaneous" -> CategoryType.Expense.Miscellaneous
            "Salary" -> CategoryType.Income.Salary
            "Investment" -> CategoryType.Income.Investment
            else -> throw IllegalArgumentException("Unknown CategoryType: $value")
        }
    }
}
