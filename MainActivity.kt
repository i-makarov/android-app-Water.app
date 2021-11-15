package com.example.ilamakarov.mywater

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.ilamakarov.mywater.Adapter.*
import com.example.ilamakarov.mywater.Preference.MyPreferenceActivity
import com.example.ilamakarov.mywater.Progress.AddProgressCommand
import com.example.ilamakarov.mywater.Wawe.WaweSettings
import com.example.ilamakarov.mywater.IObject.*
import com.example.ilamakarov.mywater.Progress.AddAdapterCommand
import com.example.ilamakarov.wave2.get_date
import com.example.ilamakarov.wave2.get_time
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import me.itangqi.waveloadingview.WaveLoadingView
import java.util.*


import android.os.Parcel
import android.os.Parcelable
import com.example.ilamakarov.mywater.Fragments_DaysMonths.FirstFragment
import com.example.ilamakarov.wave2.get_day
import com.example.ilamakarov.wave2.get_month
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

     var mProgress: Int = 0
    var mProgresss:String = "00"
    var mWaveLoadingView: WaveLoadingView? = null
    var adapter: StateAdapter? = null
    var waterCalendar: ListView? = null
     var adapterList: LinkedList<State>?  = LinkedList()


    var arr: ArrayList<Int> = arrayListOf(1,2,3,4)

    //var adapterDay: StateAdapter? = null
    //var waterCalendar: ListView? = null
     var adapterListDay: ArrayList<State>?  = ArrayList()

    val TS = IObject_Class()
    var RBlist:ArrayList<Int> = arrayListOf()
    var maxML: Int = 0

    var day = ""
    var month = ""
    var date:ArrayList<String> = ArrayList()
    var yesterday_date:ArrayList<String> = ArrayList()
    var yesterday = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

       // day = get_date().date_//.toString()
        day = get_day().date_.toString()
        month = get_month().date_.toString()
        date.add(day)
        date.add(month)

        setMaxProgress()
        mWaveLoadingView = findViewById(R.id.waveLoadingView) as WaveLoadingView
        WaweSettings(mWaveLoadingView!!).settingWawe()

        adapterList = LinkedList()
        waterCalendar = findViewById(R.id.countriesList) as ListView
        adapter = StateAdapter(this, R.layout.list_item, adapterList!!)
        waterCalendar!!.adapter = adapter
        cal = getSharedPreferences(APP_PREFERENCES2, MODE_PRIVATE)
        buttonsSetValue()

        buttonUndo()
        buttonDeleteProgress()
        buttonShowSettings()
        buttonShowFragmentActivity()

        setObject()
        postProgress()
    }

    override fun onResume() {
        super.onResume()
        setMaxProgress()
        RBlist = arrayListOf()
        loadData()
        loadProgress()
        loadAdapters()

        setObject()
        postProgress()
        //FirstFragment().progressDay = "qqq"
       // FirstFragment().adapter11!!.add(State("kk", "ml", R.drawable.water2))
        day = get_date().date_//.toInt()
        loadDate()
        loadD()

        if(day != yesterday){

            val resultDay = date[0].toInt() - yesterday_date[yesterday_date.size-2].toInt()
           // val resultMonth = day[1] - yesterday[1]
            var day = yesterday_date[yesterday_date.size-2].toInt()//date[0].toInt()
            if(resultDay>1) {
                for (i in 0..(resultDay-1)) {

                    if(i==0){

                        adapter!!.add(State(yesterday, day.toString()+"  "+yesterday_date[yesterday_date.size-2] , R.drawable.water2))

                    }else{
                        //day = day+1
                       // day=d
                            adapter!!.add(State(yesterday, (day + i).toString() + "  " + yesterday_date[yesterday_date.size - 2], R.drawable.water2))

                    }
                    resultDay-1
                }
            }
            else{
                adapter!!.add(State(yesterday, mProgress.toString(), R.drawable.water2))
            }
          // refresh()


        }

    }

    override fun onPause() {
        super.onPause()
       // RBlist = TS.getValue("ListProgress",{}) as ArrayList<Int>
       // adapterList = TS.getValue("ListAdapters", {}) as LinkedList<State>
        getObject()
        saveData()
        saveProgress()
        saveAdapters()

        yesterday = get_date().date_//.toInt()
        saveDate(cal,yesterday)
        day = get_day().date_.toString()
        month = get_month().date_.toString()
        yesterday_date.add(day)
        yesterday_date.add(month)
        saveD()


    }
    fun buttonsSetValue(){
        button125.tag=125
        button250.tag=250
        button500.tag=500
        button1000.tag=1000
    }

    fun buttonUndo() {
        button6.setOnClickListener {
            RBlist = TS.getValue("ListProgress",{}) as ArrayList<Int>

            if(RBlist.isEmpty()) {}
            else {
                AddProgressCommand(TS).undo()
                mProgress = TS.getValue("Progress", {}) as Int
                postProgress()

                AddAdapterCommand(TS).undo()
                adapterList = TS.getValue("ListAdapters", {}) as LinkedList<State>
            }

        }
    }

    private fun refresh(){
        mProgress = 0
        RBlist = arrayListOf()
        adapterList!!.clear()
        TS.setValue("ListProgress",RBlist)
        TS.setValue("Progress",mProgress)
        TS.setValue("ListAdapters",adapterList!!)
        postProgress()
    }
    fun buttonDeleteProgress(){
        val btnDeleteProgress = this.button5
        btnDeleteProgress.setOnClickListener {
            refresh()
        }
    }

    fun buttonShowSettings(){
        val btnStartProgress = this.button
        btnStartProgress.setOnClickListener {
            val intent = Intent(this, MyPreferenceActivity::class.java)
            startActivity(intent)
        }
    }

    fun buttonShowFragmentActivity(){
        val btnStartProgress = this.button_fragment
        btnStartProgress.setOnClickListener {


            val intent = Intent(this, Main2Activity::class.java)


val size = RBlist.size-1
            var value = 0
            for (i in 0..size) {
                value += RBlist[i]
            }
//intent.putExtra("adap",adapterListDay)
           // intent.putExtra("DocumentInfo", infq().DocumentInfo(adapterListDay))
            intent.putExtra("WaterProgressDay",mProgress)

            intent.putExtra("Waterarr",arr)

            startActivity(intent)


        }
    }

    fun setObject(){
        TS.setValue("ListProgress",RBlist)
        TS.setValue("Progress",mProgress)
        TS.setValue("ListAdapters",adapterList!!)
        //TS.setValue("Adapter",adapter!!)
    }

    fun getObject(){

    mProgress = TS.getValue("Progress", {}) as Int
    RBlist = TS.getValue("ListProgress",{}) as ArrayList<Int>
    adapterList = TS.getValue("ListAdapters", {}) as LinkedList<State>
    //adapter = TS.getValue("Adapter", {}) as StateAdapter
    }


    fun postProgress(){
        val strProgress = mProgress.toString() + "/" + maxML + " ml"
        mWaveLoadingView!!.centerTitle = strProgress
        mWaveLoadingView!!.progressValue = (mProgress / (maxML!! / 100))
        adapter!!.notifyDataSetChanged()
    }
    private fun setMaxProgress() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val fSize = prefs.getString(getString(R.string.pref_size), "3000")
        maxML = fSize.toInt()
    }

    fun onClick(view: View){
        val button = view as Button
        val a = button.tag as Int

        TS.setValue("AddProgress",a)

        AddProgressCommand(TS).execute()
        mProgress = TS.getValue("Progress",{}) as Int
        postProgress()
        AddAdapterCommand(TS).execute()
        adapterList = TS.getValue("ListAdapters", {}) as LinkedList<State>
    }

// попытаться сделать ввиде класса или подключить базу данных
    private fun saveData() {
        val shardPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = shardPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(RBlist)
        editor.putString("task list", json)
        editor.apply()
    }
    private fun loadData() {
        val shardPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        if(shardPreferences.contains("task list")) {
            val json = shardPreferences.getString("task list", "")
            val type = object : TypeToken<ArrayList<Int>>() {}.getType()
            RBlist = gson.fromJson(json, type)
        }


    }

    private fun saveAdapters() {
        val shardPreferences = getSharedPreferences("shared preferences1", Context.MODE_PRIVATE)
        val editor = shardPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(adapterList)
        editor.putString("adapters list", json)
        editor.apply()
    }
    private fun loadAdapters() {
        val shardPreferences = getSharedPreferences("shared preferences1", Context.MODE_PRIVATE)
        val gson = Gson()
        if(shardPreferences.contains("adapters list")) {
            val json = shardPreferences.getString("adapters list", "")
            val type = object : TypeToken<LinkedList<State>>() {}.getType()
            adapterList = gson.fromJson(json, type)
            adapter = StateAdapter(this, R.layout.list_item, adapterList!!)
            waterCalendar!!.adapter = adapter
            adapter!!.notifyDataSetChanged()
        }


    }


    private fun  saveProgress(){
        val progressPreferences = getSharedPreferences("progress preferences", Context.MODE_PRIVATE)
        val editor = progressPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(mProgress)//.toInt()
        editor.putString("progress", json)
        editor.apply()
    }
    private fun loadProgress() {
        val progressPreferences = getSharedPreferences("progress preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = progressPreferences.getString("progress", "0")
        val type = object : TypeToken<Any>() {}.getType()
        mProgress = gson.fromJson(json, type)
    }

    private fun saveDate(cal:SharedPreferences, value:String){
        val editor1 = cal.edit()
        editor1.putString(APP_PREFERENCES_COUNTER2, value).apply()


    }
    private fun loadDate(){

        if (cal.contains(APP_PREFERENCES_COUNTER2)) {
           yesterday  = cal.getString(APP_PREFERENCES_COUNTER2, "") // получили старую дату

        }

    }
    private fun  saveD(){
        val progressPreferences = getSharedPreferences("progress preferences2", Context.MODE_PRIVATE)
        val editor = progressPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(yesterday_date)//.toInt()
        editor.putString("datee", json)
        editor.apply()
    }
    private fun loadD() {
        val progressPreferences = getSharedPreferences("progress preferences2", Context.MODE_PRIVATE)
        val gson = Gson()
        if (progressPreferences.contains("datee")){
        val json = progressPreferences.getString("datee", "0")
        val type = object : TypeToken<Any>() {}.getType()
        yesterday_date = gson.fromJson(json, type)
    }
    }

    private  lateinit var cal: SharedPreferences
    private  val APP_PREFERENCES2 = "mysettings2"
    private val APP_PREFERENCES_COUNTER2 = "counter2"
}