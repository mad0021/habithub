package com.dennnisver4.habithub.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_objectives")
data class MonthlyObjective(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val yearMonth: String, // Formato: "2025-07" para Julio 2025
    val orderIndex: Int = 0 // Para mantener el orden personalizado
)
