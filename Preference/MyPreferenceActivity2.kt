package com.example.ilamakarov.mywater.Preference

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.preference.PreferenceActivity
//import com.example.ilamakarov.testarray.MainActivity
//import android.support.v4.content.ContextCompat.startActivity
import com.example.ilamakarov.mywater.R

class MyPreferenceActivity2 : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_profile)
        var btnStartProgress5 = this.button

        //var progressBar = this.progressBar

        //  btnStartProgress5.setOnClickListener { v ->
        //   showSettings4()
        //dummy = dummy + 125
        //progressBarStatus = dummy
        //postProgress(dummy)
        //progressBar.progress = progressBarStatus
        //}

    }


    // fun showSettings4() {
    //   val intent = Intent(this, MyPreferenceActivity::class.java)
    // startActivity(intent)
    //}

}

/* var adapter: StateAdapter? = null
    var waterCalendar: ListView? = null
    var adapterList: LinkedList<State>?  = LinkedList()
override fun onCreate(savedInstanceState: Bundle?) {

   super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_settings)


    adapterList = LinkedList()
    waterCalendar = findViewById(R.id.settingsList) as ListView
    adapter = StateAdapter(this, R.layout.list_item, adapterList!!)
    waterCalendar!!.adapter = adapter

    adapter!!.add(State("Профиль", "" + "  ", R.drawable.water2))*/