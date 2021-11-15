package com.example.ilamakarov.mywater.Fragments_DaysMonths

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.ilamakarov.mywater.Adapter.*
import java.util.*
import com.example.ilamakarov.mywater.Main2Activity
import com.example.ilamakarov.mywater.R



//import info.androidhive.materialtabs.R





class SecondFragment : Fragment() {
var str:String = "1"
    public var zametki111: LinkedList<State>?  = LinkedList()
    var adapter11: StateAdapter? = null
    var waterCalendar: ListView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val v =inflater!!.inflate(R.layout.fragment_second, container, false)
        val activity = activity as Main2Activity
       // zametki111 = activity.getMyDatal()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view!!, savedInstanceState)
       // waterCalendar = getView()!!.findViewById(R.id.WaterCalendarDays) as ListView
        //zametki111 = LinkedList()
        // listProgress = ArrayList()


        //val textView = view!!.findViewById(R.id.textfrag) as TextView
        //textView.setText(str)

    }

}
