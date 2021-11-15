package com.example.ilamakarov.mywater.Preference

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.preference.PreferenceActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.ilamakarov.mywater.Adapter.StateAdapter
//import com.example.ilamakarov.testarray.MainActivity
//import android.support.v4.content.ContextCompat.startActivity
import com.example.ilamakarov.mywater.R
import com.example.ilamakarov.mywater.Adapter.*
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener



class MyPreferenceActivity : AppCompatActivity() {
    var adapter: StateAdapter? = null
    var waterCalendar: ListView? = null
    var adapterList: LinkedList<State>?  = LinkedList()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)


        adapterList = LinkedList()
        waterCalendar = findViewById(R.id.settingsList) as ListView
        adapter = StateAdapter(this, R.layout.list_item_settings, adapterList!!)
        waterCalendar!!.adapter = adapter

        adapter!!.add(State("Профиль", "" + "  ", R.drawable.iconprofile4))

        waterCalendar!!.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            Log.d("", "itemClick: position = " + position + ", id = "
                    + id)
            val intent = Intent(this, MyPreferenceActivity2::class.java)
            startActivity(intent)
        })
    }
    fun onClick(view: View){

        val btnStartProgress = this.waterCalendar
        btnStartProgress!!.setOnClickListener {
            val intent = Intent(this, MyPreferenceActivity2::class.java)
            startActivity(intent)
        }
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