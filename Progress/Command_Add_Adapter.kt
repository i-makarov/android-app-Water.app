package com.example.ilamakarov.mywater.Progress

import android.os.Parcel
import android.os.Parcelable
import com.example.ilamakarov.mywater.IObject.IObject_Interfase
import com.example.ilamakarov.mywater.IObject.ObjectGetException
import com.example.ilamakarov.wave2.Interface_Command.ICommand
import java.util.*
import com.example.ilamakarov.mywater.Adapter.*
import com.example.ilamakarov.mywater.R
import com.example.ilamakarov.wave2.get_time


class AddAdapterCommand(private val obj: IObject_Interfase): ICommand {
    override fun execute() {

        try {
            val addProgress = obj.getValue("AddProgress",{}) as Int
            val adapterList = obj.getValue("ListAdapters",{}) as LinkedList<State>
            val  data = get_time().time_.toString()

            adapterList.addFirst(State(data,addProgress.toString()+"ml", R.drawable.water2))
            obj.setValue("ListAdapters",adapterList)
        }
        catch(e: ObjectGetException){
            // throw CommandException("Command can't be done")
        }

    }
    override fun undo() {
        try {
            val adapterList = obj.getValue("ListAdapters",{}) as LinkedList<State>
            val size = adapterList.size
            if (size>0) {
                adapterList.removeFirst()
            }
            obj.setValue("ListAdapters",adapterList)
        }
        catch(e: ObjectGetException){
            // throw CommandException("Command can't be done")
        }

    }

}
