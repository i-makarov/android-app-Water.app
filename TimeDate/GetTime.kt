package com.example.ilamakarov.wave2

import java.text.SimpleDateFormat
import java.util.*

class get_time{

    private val c: Calendar = Calendar.getInstance()
    private val df: SimpleDateFormat = SimpleDateFormat("HH:mm");
    private val formattedDate = df.format(c.getTime());
    val time_ = formattedDate//Calendar.HOUR

    val M = Calendar.MINUTE//Date().minutes
    val S = Calendar.SECOND//Date().seconds

}
class get_date{
    val c:Calendar = Calendar.getInstance()
    val df: SimpleDateFormat = SimpleDateFormat("dd.MM");
    val formattedDate = df.format(c.getTime());
    val date_ = formattedDate//Calendar.HOUR
}
class get_dateM{
    val c: Calendar = Calendar.getInstance()
    val df: SimpleDateFormat = SimpleDateFormat("MM");
    val formattedDate = df.format(c.getTime());
    val dateM_ = formattedDate//Calendar.HOUR
}
class get_day{
    val c:Calendar = Calendar.getInstance()
    val df: SimpleDateFormat = SimpleDateFormat("dd");
    val formattedDate = df.format(c.getTime());
    val date_ = formattedDate//Calendar.HOUR
}
class get_month{
    val c:Calendar = Calendar.getInstance()
    val df: SimpleDateFormat = SimpleDateFormat("MM");
    val formattedDate = df.format(c.getTime());
    val date_ = formattedDate//Calendar.HOUR
}