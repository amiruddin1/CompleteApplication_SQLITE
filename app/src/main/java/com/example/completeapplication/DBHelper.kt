package com.example.completeapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object{
        private const val DB_NAME = "ProductDB"
        private const val TB_NAME = "Product"
        private const val DB_VERSION = 1
        private const val ID = "Id"
        private const val NAME = "name"
        private const val DESC = "description"

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "Create Table $TB_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $DESC TEXT)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun Insert(product:Product) : Long
    {
        var db = this.writableDatabase
        var cn = ContentValues()
        cn.put(NAME,product.name)
        cn.put(DESC,product.description)

        var result = db.insert(TB_NAME,null,cn)
        return result;
    }

    fun Update(p:Product)
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME,p.name)
        cv.put(DESC,p.description)
        var flag=db.update(TB_NAME,cv,"$ID=${p.id}",
            null)
        db.close()
    }

    fun Delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_NAME,"$ID=$id",null)
        db.close()
    }

    fun RetrieveAll():ArrayList<Product>
    {
        var arr=ArrayList<Product>()
        var db=readableDatabase
        var cursor=db.query(TB_NAME,null,null,null,null,null,null)
        while(cursor.moveToNext())
        {

            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var desc=cursor.getString(2)
            var p= Product(id,name,desc)
            arr.add(p)
        }
        return arr

    }
}