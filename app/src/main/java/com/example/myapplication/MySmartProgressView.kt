package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 *
 * @Author chrisxu
 * @Date 2021/2/4
 * @Version
 **/
class MySmartProgressView (context: Context, attributeSet: AttributeSet?, style:Int) : View(context,attributeSet,style){

    constructor(context: Context, attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)

    private var parentWidth : Int = 0
    private var outerCircleStrokeWidth = 0
    private var outerShaderWidth = 0
    private var isCleanMode = false
    private var isKeepWare = false
    private var radialGradientColors = intArrayOf(0,0,0,0,0,0)

    /**
     * 温度环的颜色属性
     */
    private val insideColor1 = getContext().getColor(R.color.progress_inside_color1)
    private val outsizeColor1 = getContext().getColor(R.color.progress_outsize_color1)
    private val progressColor1 = getContext().getColor(R.color.progress_progress_color1)
    private val pointColor1 = getContext().getColor(R.color.progress_point_color1)
    private val bgCircleColor1 = getContext().getColor(R.color.progress_bg_circle_color1)
    private val indicatorColor1 = getContext().getColor(R.color.progress_indicator_color1)
    private val insideColor2 = getContext().getColor(R.color.progress_inside_color2)
    private val outsizeColor2 = getContext().getColor(R.color.progress_outsize_color2)
    private val progressColor2 = getContext().getColor(R.color.progress_progress_color2)
    private val pointColor2 = getContext().getColor(R.color.progress_point_color2)
    private val bgCircleColor2 = getContext().getColor(R.color.progress_bg_circle_color2)
    private val indicatorColor2 = getContext().getColor(R.color.progress_indicator_color2)
    private val insideColor3 = getContext().getColor(R.color.progress_inside_color3)
    private val outsizeColor3 = getContext().getColor(R.color.progress_outsize_color3)
    private val progressColor3 = getContext().getColor(R.color.progress_progress_color3)
    private val pointColor3 = getContext().getColor(R.color.progress_point_color3)
    private val bgCircleColor3 = getContext().getColor(R.color.progress_bg_circle_color3)
    private val indicatorColor3 = getContext().getColor(R.color.progress_indicator_color3)
    private val insideColor4 = getContext().getColor(R.color.progress_inside_color4)
    private val outsizeColor4 = getContext().getColor(R.color.progress_outsize_color4)
    private val progressColor4 = getContext().getColor(R.color.progress_progress_color4)
    private val pointColor4 = getContext().getColor(R.color.progress_point_color4)
    private val bgCircleColor4 = getContext().getColor(R.color.progress_bg_circle_color4)
    private val indicatorColor4 = getContext().getColor(R.color.progress_indicator_color4)
    private val insideColor5 = getContext().getColor(R.color.progress_inside_color5)
    private val outsizeColor5 = getContext().getColor(R.color.progress_outsize_color5)
    private val progressColor5 = getContext().getColor(R.color.progress_progress_color5)
    private val pointColor5 = getContext().getColor(R.color.progress_point_color5)
    private val bgCircleColor5 = getContext().getColor(R.color.progress_bg_circle_color5)
    private val indicatorColor5 = getContext().getColor(R.color.progress_indicator_color5)
    private val insideColorClean = getContext().getColor(R.color.progress_inside_color_clear)
    private val outsizeColorClean = getContext().getColor(R.color.progress_outsize_color_clear)
    private val progressColorClean = getContext().getColor(R.color.progress_progress_color_clear)
    private val pointColorClean = getContext().getColor(R.color.progress_point_color_clear)
    private val bgCircleColorClean = getContext().getColor(R.color.progress_bg_circle_color_clear)
    private val indicatorColorClean = getContext().getColor(R.color.progress_indicator_color_clear)
    private var indicatorColor : Int = 0

    constructor(context: Context,parentWidth :Int,outerShaderWidth:Int,circleStrokeWidth:Int,cleanMode:Boolean,
                isKeepWare:Boolean):this(context){
        this.parentWidth = parentWidth
        this.outerCircleStrokeWidth = circleStrokeWidth
        this.outerShaderWidth = outerShaderWidth
        this.isCleanMode = cleanMode
        this.isKeepWare = isKeepWare
        init()
    }

    fun init(){
        initView()

//        initPaint()
//
//        initBitmap()
//
//        initAnim()
    }

    private fun initView(){
        indicatorColor = indicatorColor1
        val transparentColor = Color.parseColor("#00000000")

    }

    init {

    }
}
