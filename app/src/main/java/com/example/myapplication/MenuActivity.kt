package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.aige.AigeTestActivity
import com.example.myapplication.pullleftLoadmore.PullToMoreActivity

/**
 *
 * @Author chrisxu
 * @Date 2021/2/24
 * @Version
 **/
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun onClick(view:View?){
        when(view!!.id){
            R.id.btn_aige -> {
                startPage(AigeTestActivity::class.java)
            }

            R.id.btn_pull_load_more -> {
                startPage(PullToMoreActivity::class.java)
            }
        }
    }

    private fun startPage(clazz: Class<*>){
        startActivity(Intent(this,clazz))
    }
}