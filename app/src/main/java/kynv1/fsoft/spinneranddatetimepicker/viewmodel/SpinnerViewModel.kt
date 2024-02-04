package kynv1.fsoft.spinneranddatetimepicker.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar

class SpinnerViewModel:ViewModel() {
    private val _time = MutableLiveData("")
    var time:LiveData<String> = _time

    fun selectDateTime(context:Context){
        var time = ""
        val currentDataTime = Calendar.getInstance()
        val startYear = currentDataTime.get(Calendar.YEAR)
        val startMonth = currentDataTime.get(Calendar.MONTH)
        val startDay = currentDataTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDataTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDataTime.get(Calendar.MINUTE)

        DatePickerDialog(context, { _, year, month, day ->
            TimePickerDialog(context, { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                val monthStr: String
                if ((month + 1).toString().length == 1) {
                    monthStr = "0${month + 1}"
                } else {
                    monthStr = month.toString()
                }
                time = "$day - $monthStr - $year $hour:$minute"
                updateDateTime(time)
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).show()
    }

    fun updateDateTime(dataTime:String){
        _time.value = dataTime
    }
}