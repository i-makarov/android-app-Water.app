package com.example.ilamakarov.mywater.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ilamakarov.mywater.R


class StateAdapter(context: Context, private val layout: Int, private val states: List<State>) : ArrayAdapter<State>(context, layout, states) {

    val inflater: LayoutInflater

    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = inflater.inflate(this.layout, parent, false)

        val flagView = view.findViewById<View>(R.id.image) as ImageView
        val timeView = view.findViewById<View>(R.id.time) as TextView
        val nameView = view.findViewById<View>(R.id.name) as TextView

        val state = states[position]

        flagView.setImageResource(state.imageResource)
        timeView.text = state.time
        nameView.text = state.name

        return view
    }
}