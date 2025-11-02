package com.dennnisver4.habithub.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "objective_completions",
    primaryKeys = ["objectiveId", "date"],
    foreignKeys = [
        ForeignKey(
            entity = MonthlyObjective::class,
            parentColumns = ["id"],
            childColumns = ["objectiveId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("objectiveId"), Index("date")]
)
data class ObjectiveCompletion(
    val objectiveId: Long,
    val date: String, // Formato: "2025-07-15"
    val isCompleted: Boolean = false
)
