package com.example.completeapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.myrecyclerview.view.*
import com.example.completeapplication.R

class MyAdapter(val context:Context,var arr:ArrayList<Product>)
    :RecyclerView.Adapter<MyAdapter.ViewHolder>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.myrecyclerview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgDelete.setOnClickListener {
            Toast.makeText(context,"working",Toast.LENGTH_LONG).show()
            if(context is HomePage)
            {
                Toast.makeText(context,"working me 2",Toast.LENGTH_LONG).show()
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener {
            Toast.makeText(context,"working",Toast.LENGTH_LONG).show()
            if(context is HomePage)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class ViewHolder(var view:View):RecyclerView.ViewHolder(view)
    {
        fun bind(p:Product)
        {
            view.txtID.setText(p.id.toString())
            view.txtName.setText(p.name)
            view.txtDesc.setText(p.description)

        }
    }
}