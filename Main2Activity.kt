package com.example.ilamakarov.mywater

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main2.*
import com.example.ilamakarov.wave2.*
import java.util.*
import me.itangqi.waveloadingview.WaveLoadingView
//import com.example.ilamakarov.wave2.R.id.textView
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import android.support.v4.view.ViewPager

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
//import com.example.ilamakarov.wave2.FirstFragment
import android.app.Activity;

import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout


import kotlinx.android.synthetic.main.activity_main.*


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.*
import com.example.ilamakarov.mywater.Adapter.*
import com.example.ilamakarov.mywater.Fragments_DaysMonths.MyPagerAdapter
import com.example.ilamakarov.mywater.IObject.IObject_Class
import kotlinx.android.synthetic.main.fragment_first.*


import java.lang.reflect.Array


//import com.example.ilamakarov.wave2.R.id.toolbar




class Main2Activity : AppCompatActivity() {

    var mWaveLoadingView: WaveLoadingView? = null
    private var zametki11: LinkedList<State>?  = LinkedList()
    private var thisDayArray: ArrayList<State>? = null
    var thisDay: ListView? = null
    var adapterDay: StateAdapter? = null

    var arr: ArrayList<Int> = ArrayList()
    private var adapterListDay: java.util.ArrayList<State>?  = java.util.ArrayList()

    var value = 0

    var adapter11: StateAdapter? = null
    var waterCalendar: ListView? = null

    var progressDay: Int= 0

    var lastDayDate:String = ""


    fun getMyData(): ArrayList<Int> {
        return arr
    }

    fun getMyDatal():IObject_Class {
val obj = IObject_Class()
        obj.setValue("ThisDayArray",thisDayArray!!)
        return obj
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mWaveLoadingView = findViewById(R.id.waveLoadingView) as WaveLoadingView
        mWaveLoadingView!!.progressValue = 0
        mWaveLoadingView!!.centerTitle = ""
        mWaveLoadingView!!.setAmplitudeRatio(14)
        mWaveLoadingView!!.setAnimDuration(7000)
        mWaveLoadingView!!.setWaveColor(Color.parseColor("#A4D0F2"));//00ADDA #85c6f9


val waterDayValue = IObject_Class()
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)

        thisDayArray = ArrayList()
        thisDay = findViewById(R.id.ThisDay) as ListView
        adapterDay = StateAdapter(this, R.layout.list_item, thisDayArray!!)
        thisDay!!.adapter = adapterDay

        val arguments = intent.extras
       // progressDay = intent.extras!!.getInt("ProgressDay")
        if (arguments != null) {
            value = arguments.getInt("WaterProgressDay")
            waterDayValue.setValue("Value",value)

            arr = arguments.getIntegerArrayList("Waterarr")
           // adapterListDay = intent
            //        .getSerializableExtra("adap") as ArrayList<State>

            adapterDay!!.add(State(arr[1].toString(), value.toString() + "ml", R.drawable.water2))
            adapterDay!!.notifyDataSetChanged()
            val wave = value/100
            mWaveLoadingView!!.progressValue = wave

        }

    }

    fun addAdapter(){

        adapter11!!.add(State(get_date().date_.toString(), progressDay.toString() + "ml", R.drawable.water2))
        adapter11!!.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onResume() {
        super.onResume()
    }
    private fun progressRefresh() {
        var c = 0
        if (cal.contains(APP_PREFERENCES_COUNTER2)) {
            // Получаем число из настроек
            c = cal.getInt(APP_PREFERENCES_COUNTER2, 0) // получили старую дату
            // m = cal.getInt(APP_PREFERENCES_COUNTER2, 2)
        }

        val currentTime1 = Date().date
        if (currentTime1 != c) {
            adapter11!!.add(State(get_date().date_.toString(), progressDay.toString() + "ml", R.drawable.water2))
            adapter11!!.notifyDataSetChanged()


        }
    }
    val APP_PREFERENCES2 = "mysettings2"
    val APP_PREFERENCES_COUNTER2 = "counter2"
    private  lateinit var cal: SharedPreferences
    private fun saveData() {
        val shardPreferences = getSharedPreferences("WaterCalendarPref", Context.MODE_PRIVATE)
        val editor = shardPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(zametki11)
        editor.putString("WaterCalendar", json)
        editor.apply()
    }
    private fun loadData() {
        val shardPreferences = getSharedPreferences("WaterCalendarPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = shardPreferences.getString("WaterCalendar", null)
        val type = object : TypeToken<LinkedList<State>>() {}.getType()
        zametki11 = gson.fromJson(json, type)
        if (zametki11 == null) {
            zametki11 = LinkedList()

        }
        adapter11 = StateAdapter(this, R.layout.list_item, zametki11!!)
        waterCalendar!!.adapter = adapter11


    }
    private fun  saveLastDayDate(){
        val progressPreferences = getSharedPreferences("LastDayDatePref", Context.MODE_PRIVATE)
        val editor = progressPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(lastDayDate)//.toInt()
        editor.putString("LastDayDate", json)
        editor.apply()

    }

    private fun loadLastDayDate() {
        val shardPreferences = getSharedPreferences("LastDayDatePref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = shardPreferences.getString("LastDayDate", "0")
        val type = object : TypeToken<String>() {}.getType()
        lastDayDate = gson.fromJson(json, type)

    }

}

