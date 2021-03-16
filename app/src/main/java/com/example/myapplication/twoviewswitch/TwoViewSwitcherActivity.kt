package com.example.myapplication.twoviewswitch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_two_switch_view.*

class TwoViewSwitcherActivity : AppCompatActivity(){

    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_switch_view)
    }

    fun onClick(view: View?){
        if(count % 2 == 0){
            two_view_switcher.switchToFirstAnim()
        }else{
            two_view_switcher.switchToSecondAnim()
        }
        count ++
    }
}