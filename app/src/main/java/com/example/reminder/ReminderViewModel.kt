package com.example.reminder

import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ReminderViewModel: ViewModel() {
    val dataList = mutableListOf<ItemType>()
}