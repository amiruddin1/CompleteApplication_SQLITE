package com.example.completeapplication

import android.content.Context
import java.lang.reflect.Constructor

data class Product constructor(var name:String, var description:String) {
    var id:Int = 0
    constructor(id:Int, name: String,description: String): this(name,description)
    {
        this.id=id
    }
}