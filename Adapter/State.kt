package com.example.ilamakarov.mywater.Adapter

class State(var time: String?, var name: String?, var imageResource: Int){

    fun getValueML():Int{

        return  name!!.toInt()
    }
}
