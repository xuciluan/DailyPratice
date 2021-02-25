package com.example.myapplication.animview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.R

/**
 *
 * @Author chrisxu
 * @Date 2021/2/4
 * @Version
 **/
class NumberView (context: Context, attributeSet: AttributeSet?, style:Int) : FrameLayout(context,attributeSet,style){
    constructor(context: Context, attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)

    companion object{
        const val UP_ANIMATION_MODE = 2222
        const val DOWN_ANIMATION_MODE = 2223
        const val DELAY_DURATION = 200
        val POSITION_ONE = "POSITION_ONE"
        val POSITION_TOW = "POSITION_TOW"
        val POSITION_THREE = "POSITION_THREE"
        val POSITION_FORT = "POSITION_FORT"
        val POSITION_FIVE = "POSITION_FIVE"
        val POSITION_SIX = "POSITION_SIX"
    }
    private lateinit var mTvFirst: TextView
    private lateinit var mTvSecond: TextView
    private lateinit var anim : ValueAnimator
    private var upOrDown =
        DOWN_ANIMATION_MODE
    private var mHeight = 0
    private var mTrueValue = 0
    private var mCurrentValue = 0
    private var startDelay = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_progress_number_item_layout,this,true)
        mTvFirst = findViewById(R.id.tv_number_one)
        mTvSecond = findViewById(R.id.tv_number_tow)
        init()
    }

    private fun init(){
        anim = ValueAnimator.ofFloat(0.0f,1.0f)
        anim.duration = 100
        anim.interpolator = OvershootInterpolator()
        anim.repeatCount = 0
        anim.repeatMode = ValueAnimator.RESTART
        anim.addUpdateListener {
            val value  = it.animatedValue as Float
            if(upOrDown == UP_ANIMATION_MODE){
                //向上移动
                mTvFirst.translationY = -mHeight * value
                mTvSecond.translationY = -mHeight * value
            }else{
                //向下移动
                mTvFirst.translationY = mHeight * value
                mTvSecond.translationY = mHeight * value
            }
        }
    }

    fun setCurrentValue(position:String, value:Int,trueValue:Int){
        if(trueValue > mTrueValue){
            upOrDown = UP_ANIMATION_MODE
        }else{
            upOrDown = DOWN_ANIMATION_MODE
        }
        this.mTrueValue = trueValue
        setValue(position,value,upOrDown)
    }

    private fun setValue(position: String,value:Int,mode:Int){
        if(value == mCurrentValue){
            return
        }

        upOrDown = mode
        setStartDelay(position)

        mTvFirst.text = mCurrentValue.toString()
        mTvSecond.text = mCurrentValue.toString()
        if(anim.isRunning){
            anim.cancel()
        }
        anim.duration = (400 + startDelay).toLong()
        mTvFirst.translationY = 0f
        mTvSecond.translationY = 0f
        anim.start()
        mCurrentValue = value
    }

    private fun setStartDelay(position: String){
        if (position.equals(POSITION_ONE)){
            startDelay = 0
        }else if(position.equals(POSITION_TOW)){
            startDelay = DELAY_DURATION
        }
    }


    fun setLayoutSize(w:Int,h:Int,s:Int){
        if(width == w){
            return
        }
        mHeight = h
        val containerLp = LinearLayout.LayoutParams(w,h)
        layoutParams  = containerLp
        val tvFirstLp = FrameLayout.LayoutParams(w,h)
        tvFirstLp.gravity = Gravity.CENTER
        mTvFirst.layoutParams = tvFirstLp
        mTvFirst.gravity = Gravity.CENTER
        mTvFirst.setTextSize(s.toFloat())


        val tvSecond = FrameLayout.LayoutParams(w,h)
        tvSecond.topMargin = h
        tvSecond.gravity = Gravity.CENTER
        mTvSecond.layoutParams = tvSecond
        mTvSecond.gravity = Gravity.CENTER
        mTvSecond.setTextSize(s.toFloat())
    }
}