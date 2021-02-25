package com.example.myapplication.animview

import com.example.myapplication.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @Author chrisxu
 * @Date 2021/2/4
 * @Version
 **/
class AnimNumberView  (context: Context, attributeSet: AttributeSet?, style:Int) : LinearLayout(context,attributeSet,style) {
    constructor(context: Context, attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)

    private val singleNum : NumberView
    private val tenNum : NumberView
    private val numberHeight = dip2px(context, 46f)
    private val numberWidth = dip2px(context, 95f)
    private val numberTextSize = 80
    private var currentTemp = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_progress_layout,this,true)
        singleNum = findViewById(R.id.singleNum)
        tenNum = findViewById(R.id.tenNum)
        setLayoutSize(numberWidth,numberHeight,numberTextSize)

    }

    private fun setLayoutSize(width:Int,height:Int,textSize:Int){
        singleNum.setLayoutSize(width,height,textSize)
        tenNum.setLayoutSize(width,height,textSize)
    }

    fun setTemperature(temp : Int ,mode:Int){
        disposetTimer()
        this.currentTemp = temp
        setLayoutSize(width,height,numberTextSize)
        val single = temp % 10
        val ten = temp /10 % 10
        if (temp < 10) run {
            singleNum.setVisibility(View.VISIBLE)
            tenNum.setVisibility(View.GONE)
        } else if (temp < 100) run {
            singleNum.setVisibility(View.VISIBLE)
            tenNum.setVisibility(View.VISIBLE)
        }
        singleNum.setCurrentValue(NumberView.POSITION_ONE,single,temp)
        tenNum.setCurrentValue(NumberView.POSITION_TOW,ten,temp)
    }

    private var timerDisposable :Disposable? = null
    private fun disposetTimer(){
        if(timerDisposable != null && !timerDisposable!!.isDisposed){
            timerDisposable?.dispose()
            timerDisposable = null
        }
    }

    fun setTimer(second:Int){
        disposetTimer()
        timerDisposable = Flowable.intervalRange(0, (second + 1).toLong(),
            0,1,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                setClock(it.toInt())
            }
            .doOnComplete {
                disposetTimer()
            }
            .subscribe()
    }

    private fun setClock(second:Int){
        setLayoutSize(width,height,numberTextSize)
        val single = second % 10
        val ten = second /10 % 10
        if (second < 10) run {
            singleNum.setVisibility(View.VISIBLE)
            tenNum.setVisibility(View.GONE)
        } else if (second < 100) run {
            singleNum.setVisibility(View.VISIBLE)
            tenNum.setVisibility(View.VISIBLE)
        }
        singleNum.setCurrentValue(NumberView.POSITION_ONE,single,second)
        tenNum.setCurrentValue(NumberView.POSITION_TOW,ten,second)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposetTimer()
    }
}