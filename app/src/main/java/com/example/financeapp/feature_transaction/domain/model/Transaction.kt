package com.example.financeapp.feature_transaction.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import java.time.LocalDateTime

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val dateTime: LocalDateTime,
    val type: TransactionType,
    val amount: Int,
)
