package com.example.ilamakarov.mywater.IObject


class ObjectGetException: Error("Can't return object data")

interface  IObject_Interfase {
    fun getValue(_key:String, lambda:()->Any = {throw ObjectGetException()}):Any
    fun setValue(_key:String,newvalue:Any)

}
