package com.example.ilamakarov.mywater.IObject


import java.util.*
import kotlin.NoSuchElementException

class IObject_Class :IObject_Interfase {
    private val IObject_map = hashMapOf<String, Any>()

    override fun getValue(_key:String, lambda:()->Any):Any{
        try {
            return  IObject_map.getOrDefault(_key, lambda())
        }catch(ex:Throwable) {
            throw Exception("Lambda is invalid argument")
        }
    }

    override fun setValue(_key:String, newvalue:Any){
        IObject_map.put(_key, newvalue)
    }

}