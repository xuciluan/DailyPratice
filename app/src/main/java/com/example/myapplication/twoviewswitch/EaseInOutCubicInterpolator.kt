package com.example.myapplication.twoviewswitch


class EaseInOutCubicInterpolator : android.view.animation.Interpolator{

    override fun getInterpolation(input: Float): Float {
        return getValue(input).toFloat()
    }

    private fun getValue(input: Float): Double {
        return if (input < 0.5) (4 * input * input * input).toDouble() else 1 - Math.pow(
            -2 * input + 2.toDouble(),
            3.0
        ) / 2
    }
}