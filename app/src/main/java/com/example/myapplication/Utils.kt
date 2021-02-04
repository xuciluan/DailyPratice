package com.example.myapplication

import android.content.Context

/**
 *
 * @Author chrisxu
 * @Date 2021/2/3
 * @Version
 **/
fun dip2px(context: Context,dpValue: Float): Int {
    val scale = context.getResources().getDisplayMetrics().density
    return (dpValue * scale + 0.5f).toInt()
}