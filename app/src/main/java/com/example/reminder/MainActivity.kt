package com.example.reminder

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginStart
import com.example.reminder.databinding.ActivityMainBinding
import com.example.reminder.databinding.DialogBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataArray = arrayOf(
        mutableMapOf(
            "title" to "trip",
            "desc" to "just go somewhere",
            "date" to LocalDate.of(2024, 7, 20),
            "time" to LocalTime.of(21, 45)
        ),
        mutableMapOf(
            "title" to "grocery",
            "desc" to "just buy something",
            "date" to LocalDate.of(2024, 7, 22),
            "time" to LocalTime.of(11, 45)
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnNewReminder.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val bindingDialog = DialogBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(bindingDialog.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        ////////////////////////////////////////////////////////////////////////////////////////

        val myCalendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
            bindingDialog.tvDialogDate.text = sdf.format(myCalendar.time)
            bindingDialog.tvDialogDate.setBackgroundResource(R.drawable.cancel_button_background)
        }
        bindingDialog.ibDialogCalendar.setOnClickListener {
            DatePickerDialog(this, datePickerDialog, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        ////////////////////////////////////////////////////////////////////////////////////////

        bindingDialog.ibDialogClock.setOnClickListener {

        }

        ////////////////////////////////////////////////////////////////////////////////////////

        bindingDialog.btnDialogCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}