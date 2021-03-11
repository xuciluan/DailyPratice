package com.example.myapplication.pullleftLoadmore

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View

class LoadingView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?= null, style: Int = 0) :
    View(context, attributeSet, style){

    private val paint : Paint = Paint()
    private val path = Path()
    private var lineWithL = 0f


    init {
        paint.setColor(Color.RED)
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        lineWithL = w.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawColor(Color.BLUE)

        lineWithL = width   +  mTranslation
        if(lineWithL > width / 4 * 3){
            lineWithL = width + mTranslation
        }else{
            lineWithL = width / 4 * 3.toFloat()
        }
        val y1 = height / 2f
        val control = lineWithL - mFraction * width / 2
        path.reset()
        path.moveTo(width.toFloat() ,0f)
        path.lineTo(width.toFloat() ,height.toFloat())
        path.lineTo(lineWithL,height.toFloat())
        path.quadTo(control,y1,lineWithL,0f)
//        path.lineTo(lineWithL,0f)
        path.close()

        canvas?.drawPath(path,paint)
    }

    private var mFraction = 0f
    private var mTranslation = 0f

    fun setFraction(fraction : Float, translationX : Float){
//        Log.d("xcl","fraction = " + fraction+",translation = " + translationX)
        this.mFraction = fraction
        this.mTranslation = translationX
        invalidate()
    }

}