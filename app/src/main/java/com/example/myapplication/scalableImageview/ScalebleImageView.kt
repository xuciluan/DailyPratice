package com.example.myapplication.scalableImageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.R

class ScalebleImageView  @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?= null, style: Int = 0) :
    View(context, attributeSet, style), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    private var smallScale = 0f
    private var bigScale = 0f
    private var big = false
    private val image : Bitmap
    private val gestureDetector : GestureDetectorCompat


    init {
        image = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
        gestureDetector = GestureDetectorCompat(context,this)
        gestureDetector.setOnDoubleTapListener(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if(w / h  > h / w){
            bigScale = w / h
            smallScale = h / w
        }else{
            bigScale = h / w
            smallScale = w / h
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val currentScale = if(big) bigScale else smallScale
        canvas?.scale(currentScale,currentScale,width / 2f,height/2f)
        canvas?.drawBitmap(image,)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
       return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
       return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
      return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {

    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
         return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
       return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
       return false
    }
}