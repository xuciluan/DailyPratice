package com.example.myapplication.scalableImageview

import android.content.Context
import android.util.AttributeSet
import android.view.View

class ScalebleImageView  @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?= null, style: Int = 0) :
    View(context, attributeSet, style){


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }
}