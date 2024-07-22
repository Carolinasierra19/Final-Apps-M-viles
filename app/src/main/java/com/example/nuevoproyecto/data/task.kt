package com.example.nuevoproyecto.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var title: String,
    var description: String,
    var isCompleted: Boolean = false,
    var itemsNeeded: List<String> = emptyList()
) : Parcelable




