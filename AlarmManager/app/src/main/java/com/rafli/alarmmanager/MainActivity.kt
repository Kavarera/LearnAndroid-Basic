package com.rafli.alarmmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rafli.alarmmanager.databinding.ActivityMainBinding
import com.rafli.alarmmanager.fragmentClass.DatePickerFragment
import com.rafli.alarmmanager.fragmentClass.TimePickerFragment
import com.rafli.alarmmanager.receiverClass.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener,DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private var binding : ActivityMainBinding? = null
    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //listener one time alarm
        binding?.btnOnceDate?.setOnClickListener(this)
        binding?.btnOnceTime?.setOnClickListener(this)
        binding?.btnSetOnceAlarm?.setOnClickListener(this)

        alarmReceiver= AlarmReceiver()

        //Listener repeating alarm
        binding?.btnRepeatingTime?.setOnClickListener(this)
        binding?.btnSetRepeatingAlarm?.setOnClickListener(this)

        //Listener cancel alarm
        binding?.btnCancelRepeatingAlarm?.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_once_date->{
                val datePickerFragment= DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            R.id.btn_once_time->{
                val timePickerFragment=TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }
            R.id.btn_set_once_alarm->{
                val onceDate=binding?.tvOnceDate?.text.toString()
                val onceTime=binding?.tvOnceTime?.text.toString()
                val onceMessage=binding?.edtOnceMessage?.text.toString()

                alarmReceiver.setOneTimeAlarm(this,AlarmReceiver.TYPE_ONE_TIME,
                onceDate,onceTime,onceMessage)
            }

            R.id.btn_repeating_time->{
                val timePickerFragment=TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }

            R.id.btn_set_repeating_alarm->{
                val repeatTime=binding?.tvRepeatingTime?.text.toString()
                val repeatMessage = binding?.edtRepeatingMessage?.text.toString()
                alarmReceiver.setRepeatingAlarm(this,AlarmReceiver.TYPE_REPEATING, repeatTime,repeatMessage)
            }

            R.id.btn_cancel_repeating_alarm-> alarmReceiver.cancelAlarm(this,AlarmReceiver.TYPE_REPEATING)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    override fun onDialogDateSet(tag: String?, year: Int, Month: Int, dayOfMonth: Int) {
        //siapkan date formatter terlebih dahulu
        val calendar=Calendar.getInstance()
        calendar.set(year,Month,dayOfMonth)
        val dateFormat=SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())

        //set text dari textView once
        binding?.tvOnceDate?.text=dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        //siapkan date formatter terlebih dahulu
        val calendar=Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.set(Calendar.MINUTE,minute)
        val dateFormat=SimpleDateFormat("HH:mm",Locale.getDefault())

        //set text dari textview berdasarkan tag
        when(tag){
            TIME_PICKER_ONCE_TAG->binding?.tvOnceTime?.text=dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG->{binding?.tvRepeatingTime?.text=dateFormat.format(calendar.time)}
            else->{

            }
        }
    }
}