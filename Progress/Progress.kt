package com.example.ilamakarov.mywater.Progress

import com.example.ilamakarov.mywater.IObject.IObject_Interfase
import com.example.ilamakarov.mywater.IObject.ObjectGetException
import com.example.ilamakarov.wave2.Interface_Command.ICommand


class AddProgressCommand(private val obj: IObject_Interfase): ICommand {
    override fun execute() {

        try {
            var progress = obj.getValue("Progress",{}) as Int
            val addProgress = obj.getValue("AddProgress",{}) as Int
            val listProgress = obj.getValue("ListProgress",{}) as ArrayList<Int>
            progress+=addProgress
            listProgress.add(addProgress)
            obj.setValue("Progress",progress)
            obj.setValue("ListProgress",listProgress)
        }
        catch(e: ObjectGetException){
            // throw CommandException("Command can't be done")
        }

    }
    override fun undo() {
        try {
            var progress = obj.getValue("Progress",{}) as Int

            val listProgress = obj.getValue("ListProgress",{}) as ArrayList<Int>
            val size = listProgress.size - 1
            if (size >=0){
                val deleteProgress = listProgress[size]
                listProgress.removeAt(size)
                progress-=deleteProgress}
            obj.setValue("Progress",progress)
            obj.setValue("ListProgress",listProgress)
        }
        catch(e: ObjectGetException){
            // throw CommandException("Command can't be done")
        }

    }

}
