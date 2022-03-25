package com.example.completeapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.cutomedialogue.*
import kotlinx.android.synthetic.main.myrecyclerview.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        refreshRecycler()
        var db = DBHelper(this)
        btnAdd.setOnClickListener {
            var product = Product(pname.text.toString(),pdesc.text.toString())
            if(db.Insert(product)>0)
            {
                Toast.makeText(this,"Record Inserted SuccessFully!",Toast.LENGTH_LONG).show()
                pname.setText("")
                pdesc.setText("")
            }
            else
            {
                Toast.makeText(this,"Kuchh To Garbad Hai Daya!",Toast.LENGTH_LONG).show()
            }
            refreshRecycler()
        }
    }
    fun refreshRecycler()
    {
        var db=DBHelper(this)
        var arr:ArrayList<Product> = db.RetrieveAll()
        var adapter=MyAdapter(this,arr)
        myrecyclerviewlist.adapter=adapter
    }
    fun delete(position:Int)
    {
        Toast.makeText(applicationContext,"im here",Toast.LENGTH_LONG).show()
        var db=DBHelper(this)
        var arr:ArrayList<Product> = db.RetrieveAll()
        var person=arr.get(position)
        Toast.makeText(applicationContext,"${person.id}",Toast.LENGTH_LONG).show()
        db.Delete(person.id)
        refreshRecycler()
    }
    fun update(position: Int)
    {
        var db=DBHelper(this)
        var arr:ArrayList<Product> = db.RetrieveAll()
        var product=arr.get(position)
        var dialog= Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.cutomedialogue)
        dialog.txtUpadateId.setText(product.id.toString())
        dialog.edtUpadeName.setText(product.name)
        dialog.edtUpdateAge.setText(product.description)
        // Full Screen Code::
        //___________THIS CODE IS OPTIONAL TO EXECUTE_____________
        // FROM Next line from here to next five Lines.....
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        dialog.window!!.attributes = lp
        dialog.btnUpdate.setOnClickListener {
            var id=dialog.txtUpadateId.text.toString().toInt()
            var name=dialog.edtUpadeName.text.toString()
            var desc=dialog.edtUpdateAge.text.toString()
            var p=Product(id,name,desc)
            db.Update(p)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}