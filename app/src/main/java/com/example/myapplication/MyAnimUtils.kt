package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Context
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.PathInterpolator
import android.view.animation.ScaleAnimation

/**
 *
 * @Author chrisxu
 * @Date 2021/2/3
 * @Version
 **/
object MyAnimUtils {

    fun getColorAnim(startColor:Int,endColor:Int) : ValueAnimator{
        val valueAnim = ValueAnimator.ofArgb(startColor,endColor)
        valueAnim.interpolator = PathInterpolator(0.57f,0.01f,0.41f,1.00f)
        valueAnim.duration = 500
        valueAnim.repeatCount = 1
        valueAnim.repeatMode = ValueAnimator.REVERSE
        return valueAnim
    }

    fun getScaleAnim(): ScaleAnimation {
        val scaleAnim = ScaleAnimation(1.0f,1.05f,1.0f,1.05f)
        scaleAnim.interpolator = PathInterpolator(0.57f,0.01f,0.41f,1.00f)
        scaleAnim.fillAfter = true
        scaleAnim.duration = 500
        scaleAnim.repeatCount = 1
        scaleAnim.repeatMode = Animation.REVERSE
        return scaleAnim
    }
}