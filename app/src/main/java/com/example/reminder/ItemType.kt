package com.example.reminder

import java.time.LocalTime
import java.time.Month
import java.util.UUID

data class ItemType(
    val id:UUID,
    var title:String,
    var desc:String,
    var year:Int,
    var month:Month,
    var dayOfMonth:Int,
    var time:LocalTime,
)