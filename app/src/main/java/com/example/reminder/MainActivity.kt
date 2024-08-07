package com.example.reminder

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.databinding.ActivityMainBinding
import com.example.reminder.databinding.DialogBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val reminderViewModel: ReminderViewModel by viewModels()
    private var isTitleFilled = false
    private var isDescFilled = false
    private var isDateFilled = false
    private var isTimeFilled = false

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

//        reminderViewModel = ViewModelProvider(this)[ReminderViewModel::class.java]

        if (reminderViewModel.dataList.isNotEmpty()){
            hideTvNoReminders()
        }else{
            showTvNoReminders()
        }
        val adapter = MyAdapter()
        adapter.submitList(reminderViewModel.dataList)
        binding.rvReminderList.hasFixedSize()
        binding.rvReminderList.layoutManager = LinearLayoutManager(this)
        binding.rvReminderList.adapter = adapter

        fun updateList(){
            adapter.submitList(reminderViewModel.dataList.toList())

            Log.i("List", reminderViewModel.dataList.toString())
        }

        binding.btnNewReminder.setOnClickListener {
            showDialog(::updateList)
        }
    }

    private fun showDialog(updateList:()->Unit) {

        val bindingDialog = DialogBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(bindingDialog.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isDateFilled = false
        isTimeFilled = false
        isTitleFilled = false
        isDescFilled = false

        var selectedDate = LocalDate.now()
        var selectedTime = LocalTime.now()

        ////////////////////////////////////////////////////////////////////////////////////////

        val myCalendar = Calendar.getInstance()
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            isDateFilled = true
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
            bindingDialog.tvDialogDate.text = sdf.format(myCalendar.time)
            bindingDialog.tvDialogDate.setBackgroundResource(R.drawable.cancel_button_background)
        }
        bindingDialog.ibDialogCalendar.setOnClickListener {
            val dpd = DatePickerDialog(this, onDateSetListener, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
            dpd.datePicker.minDate = System.currentTimeMillis()
            dpd.show()
        }

        ////////////////////////////////////////////////////////////////////////////////////////

        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            //// to prevent past time selection
            val cal = Calendar.getInstance().apply {
                set(Calendar.YEAR, selectedDate.year)
                set(Calendar.MONTH, selectedDate.monthValue - 1)
                set(Calendar.DAY_OF_MONTH, selectedDate.dayOfMonth)
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            if (cal.timeInMillis < System.currentTimeMillis()){
                Toast.makeText(this, "Please select a time in the future", Toast.LENGTH_SHORT).show()
            }else{
                val amOrPm = if (hourOfDay > 11) "pm" else "am"
                val hourToShow = if (hourOfDay > 12) hourOfDay - 12 else hourOfDay
                bindingDialog.tvDialogTime.text = String.format("%02d : %02d  %s", hourToShow, minute, amOrPm)
                bindingDialog.tvDialogTime.setBackgroundResource(R.drawable.cancel_button_background)
                isTimeFilled = true
                selectedTime = LocalTime.of(hourOfDay, minute)
            }
        }

        bindingDialog.ibDialogClock.setOnClickListener {
            TimePickerDialog(this, onTimeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE), false).show()
        }

        ////////////////////////////////////////////////////////////////////////////////////////

        bindingDialog.btnDialogSetReminder.setOnClickListener {
            isTitleFilled = bindingDialog.etDialogTitle.text.isNotEmpty()
            isDescFilled = bindingDialog.etDialogDesc.text.isNotEmpty()

            if (isTitleFilled && isDescFilled && isDateFilled && isTimeFilled){
                val newReminder = ItemType(
                    id = UUID.randomUUID(),
                    title = bindingDialog.etDialogTitle.text.toString(),
                    desc = bindingDialog.etDialogDesc.text.toString(),
                    year = selectedDate.year,
                    month = selectedDate.month,
                    dayOfMonth = selectedDate.dayOfMonth,
                    time = selectedTime
                )
                reminderViewModel.dataList.add(newReminder)
                updateList()
                hideTvNoReminders()
                dialog.dismiss()
            }else if (!isTitleFilled){
                Toast.makeText(this, "Title cannot be empty!", Toast.LENGTH_SHORT).show()
            }else if (!isDescFilled){
                Toast.makeText(this, "Description cannot be empty!", Toast.LENGTH_SHORT).show()
            }else if (!isDateFilled){
                Toast.makeText(this, "Please select a date first!", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Please select a time first!", Toast.LENGTH_SHORT).show()
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////

        bindingDialog.btnDialogCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun hideTvNoReminders(){
        val params = binding.tvNoReminders.layoutParams as LinearLayout.LayoutParams
        params.height = 0
        binding.tvNoReminders.layoutParams = params
    }
    private fun showTvNoReminders() {
        val params = binding.tvNoReminders.layoutParams as LinearLayout.LayoutParams
        params.height = 1800
        binding.tvNoReminders.layoutParams = params
    }
}