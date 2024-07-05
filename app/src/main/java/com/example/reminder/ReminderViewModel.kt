package com.example.reminder

import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ReminderViewModel: ViewModel() {
    val dataList = mutableListOf<ItemType>(
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
        ItemType(
            id = UUID.randomUUID(),
            title = "title",
            desc = "description",
            year = 2024,
            month = LocalDate.now().month,
            dayOfMonth = 22,
            time = LocalTime.now()
        ),
    )
}