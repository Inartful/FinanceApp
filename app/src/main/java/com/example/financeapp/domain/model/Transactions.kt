package com.example.financeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financeapp.domain.util.TransactionType
import java.time.LocalDateTime

@Entity
data class Transactions(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val dateTime: LocalDateTime,
    val type: TransactionType,
    val amount: Int,
)
