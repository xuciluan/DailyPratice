package com.example.myapplication.aige

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.R


/**
 *
 * @Author chrisxu
 * @Date 2021/2/24
 * @Version
 **/
class CustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    style: Int = 0
) : View(context, attributeSet, style) {

    private var screenW :Int = 0
    private var screenH :Int = 0
    private lateinit var path : Path
    private lateinit var paint : Paint
    private lateinit var bitmap : Bitmap
    private lateinit var mCanvas : Canvas
    private lateinit var bgBitma : Bitmap

    init {
        cal(context)
        init()
    }

    private fun cal(context: Context){
        screenH = getScreenHeight(context)
        screenW = getScreenWidth(context)
    }



    private fun init(){
        path = Path()
        paint = Paint()
        paint.setARGB(128,255,0,0)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 50f

        bitmap = Bitmap.createBitmap(screenW,screenH,Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(bitmap)
        mCanvas.drawColor(0xFF808080)

        bgBitma = BitmapFactory.decodeResource(context.resources, R.drawable.yellow_normal)
        bgBitma = Bitmap.createScaledBitmap(bgBitma,screenW,screenH,true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bitmap,0f,0f,paint)
        canvas?.drawBitmap(bgBitma,0f,0f,paint)

        mCanvas.drawPath(path,paint)
    }

    private var preX = 0f
    private var preY = 0f
    private val MIN_MOVE_DIS = 5// 最小的移动距离：如果我们手指在屏幕上的移动距离小于此值则不会绘制
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                path.reset()
                path.moveTo(event.getX(),event.y)
                preX = event.x
                preY = event.y
            }
            MotionEvent.ACTION_MOVE ->{
                var dx = Math.abs(x - preX)
                var dy = Math.abs(y - preY)
                if (dy > MIN_MOVE_DIS || dx > MIN_MOVE_DIS){
                    path.quadTo(preX,
                        preY,
                        (x + preX) / 2,
                        (y + preY) / 2)
                }
            }
        }
        invalidate()
        return true
    }

}