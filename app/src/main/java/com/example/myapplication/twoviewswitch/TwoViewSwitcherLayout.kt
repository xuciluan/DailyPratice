package com.example.myapplication.twoviewswitch

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

class TwoViewSwitcherLayout  @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?= null, style: Int = 0) :
    LinearLayout(context, attributeSet, style){


    companion object{
        private const val TO_FIRST = 0
        private const val TO_SECOND = 1
    }

    private var switchToFirstAnim : ValueAnimator?= null
    private var switchToSecondAnim : ValueAnimator?= null
    private var switchMode : Int = TO_FIRST


    init {
        orientation = VERTICAL
    }


     fun switchToFirstAnim(){

         if(switchMode == TO_FIRST) return
         if(switchToFirstAnim != null && switchToFirstAnim!!.isRunning) return
         switchMode = TO_FIRST

         val child = getChildAt(0)
        if(switchToFirstAnim == null){
            switchToFirstAnim = ValueAnimator.ofInt(child.height,0)
            switchToFirstAnim!!.addUpdateListener{
                val scrolly = it.animatedValue as Int
                scrollTo(0,scrolly)
            }
            switchToFirstAnim!!.duration = 1000
            switchToFirstAnim!!.interpolator = EaseInOutCubicInterpolator()
        }
         switchToFirstAnim!!.start()
    }

     fun switchToSecondAnim(){
         if(switchMode == TO_SECOND) return
         if(switchToSecondAnim != null && switchToSecondAnim!!.isRunning) return
         switchMode = TO_SECOND

         val child = getChildAt(0)
        if(switchToSecondAnim == null){
            switchToSecondAnim = ValueAnimator.ofInt(0,child.height)
            switchToSecondAnim!!.addUpdateListener{
                val scrolly = it.animatedValue as Int
                scrollTo(0,scrolly)
            }
            switchToSecondAnim!!.duration = 1000
            switchToSecondAnim!!.interpolator = EaseInOutCubicInterpolator()
        }
         switchToSecondAnim!!.start()
    }

}