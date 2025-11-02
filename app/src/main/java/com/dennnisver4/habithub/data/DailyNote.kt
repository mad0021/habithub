package com.dennnisver4.habithub.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily_notes")
data class DailyNote(
    @PrimaryKey
    val date: String, // Formato: "2025-07-15"
    val note: String,
    val mood: String = "" // Opcional: para añadir estado de ánimo
)

// Función auxiliar para crear desde LocalDate
fun LocalDate.toDailyNoteKey(): String = this.toString()
