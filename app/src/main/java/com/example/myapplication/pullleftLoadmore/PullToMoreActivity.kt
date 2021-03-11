package com.example.myapplication.pullleftLoadmore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_pulltoloadmore.*


class PullToMoreActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulltoloadmore)
        rv_list.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_list.adapter =ELMAdapter()

        pull_layout.addView(resources.getDimensionPixelOffset(R.dimen.item_img))
    }

    class ELMAdapter : RecyclerView.Adapter<ELMAdapter.ViewHolder>(){

         class  ViewHolder(val itemView:View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_elm,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 8
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        }

    }
}