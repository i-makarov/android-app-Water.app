package com.example.ilamakarov.mywater.Fragments_DaysMonths
import android.R.attr.*
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import com.example.ilamakarov.wave2.*
import java.util.*
import kotlinx.android.synthetic.main.fragment_first.*

//import com.example.ilamakarov.wave2.R.id.textView
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.itangqi.waveloadingview.WaveLoadingView
import java.util.*
import android.content.IntentFilter
import android.content.BroadcastReceiver
import android.net.sip.SipSession
import android.widget.*
import android.support.v4.view.ViewCompat.*
import android.util.Log

import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import com.example.ilamakarov.mywater.Adapter.*
import com.example.ilamakarov.mywater.IObject.IObject_Class
import com.example.ilamakarov.mywater.Main2Activity
import com.example.ilamakarov.mywater.MainActivity
import com.example.ilamakarov.mywater.R
import org.jetbrains.annotations.Nullable
import kotlinx.android.synthetic.main.fragment_first.buttonClearFrg

class FirstFragment : Fragment() {
    private var zametki11: LinkedList<State>? = null// = ArrayList()
    private var thisDayArray: ArrayList<State>? = null
    var thisDay: ListView? = null
    var adapterDay: StateAdapter? = null
    var zametki111: LinkedList<State>?  = LinkedList()
    var adapter11: StateAdapter? = null
    var waterCalendar: ListView? = null

    var arr: ArrayList<Int> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_first, container, false)
        val activity = activity as Main2Activity
        var obj = IObject_Class()
        obj = activity.getMyDatal()
        thisDayArray =  obj.getValue("ThisDayArray",{}) as ArrayList<State>
        thisDay = view.findViewById(R.id.ThisDay) as ListView
        adapterDay = StateAdapter(this.context!!, R.layout.list_item, thisDayArray!!)
        thisDay!!.adapter = adapterDay

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        waterCalendar = view!!.findViewById(R.id.WaterCalendarDays) as ListView

        adapter11 = StateAdapter(this.context!!, R.layout.list_item, zametki111!!)
        waterCalendar!!.adapter = adapter11
        adapter11!!.notifyDataSetChanged()


        val btnStartProgress7 = this.buttonClearFrg
        btnStartProgress7.setOnClickListener { v ->
            adapter11!!.clear()
        }
    }
}

