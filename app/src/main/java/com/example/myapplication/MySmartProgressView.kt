package com.example.myapplication

import android.content.Context
import android.graphics.*
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
    /**
     * 保温的进度圆变色值
     */
    private val mLinearGradientColor1 = getContext().getColor(R.color.progress_linearGradient_color1)
    private val mLinearGradientColor2 = getContext().getColor(R.color.progress_linearGradient_color2)

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
        initPaint()
        initBitmap()
//        initAnim()
    }

    private var centerX  = 0
    private var centerY = 0
    private var radius = 0

    private fun initView(){
        indicatorColor = indicatorColor1
        val transparentColor = Color.parseColor("#00000000")
        radialGradientColors[0] = transparentColor
        radialGradientColors[1] = transparentColor
        radialGradientColors[2] = transparentColor
        radialGradientColors[3] = transparentColor

        centerX = parentWidth / 2
        centerY = parentWidth / 2

        //粒子圆环的宽度
        radius = centerX - outerCircleStrokeWidth/2 - outerShaderWidth
    }

    private val sweepPaint = Paint()
    private val mRadialGradientStops = floatArrayOf(0f, 0.69f, 0.86f, 0.94f, 0.98f, 1f)
    private lateinit var radialGradient : RadialGradient
    private lateinit var sweepGradient : SweepGradient
    private val arcPath : Path = Path()
    private val outerCirclePaint = Paint()
    private val pointPaint = Paint()
    private val backCirclePaint = Paint()
    private val backShadePaint = Paint()
    private val bmpPaint = Paint()
    private lateinit var bitmapDst : Bitmap

    private fun initPaint(){
        sweepPaint.isAntiAlias = true
        sweepPaint.style = Paint.Style.FILL_AND_STROKE
        radialGradient = RadialGradient(0f,0f,centerX.toFloat(),radialGradientColors,mRadialGradientStops,
            Shader.TileMode.CLAMP)

        sweepPaint.setShader(radialGradient)

        //step1：初始化外层圆环的画笔
        outerCirclePaint.style = Paint.Style.STROKE
        outerCirclePaint.isAntiAlias = true
        outerCirclePaint.strokeWidth = outerCircleStrokeWidth.toFloat()

        val gradientColors = intArrayOf(mLinearGradientColor2,mLinearGradientColor2,
            mLinearGradientColor1,mLinearGradientColor1,
            mLinearGradientColor2,mLinearGradientColor2
            )
        val positions = floatArrayOf(0f,0.05f,0.12f,0.88f,0.95f,1f)
        sweepGradient = SweepGradient(0f,0f,gradientColors,positions)

        //stop2：运动粒子的画笔

        //step3：初始化底色圆画笔
        backCirclePaint.isAntiAlias = true
        backCirclePaint.strokeWidth = outerCircleStrokeWidth.toFloat()
        backCirclePaint.style = Paint.Style.STROKE
        //初始化底色盘的initAnimator画笔
        backShadePaint.isAntiAlias = true
        backShadePaint.color = bgCircleColor1

        //指针画笔
    }

    private fun initBitmap(){
        val f = 130f / 656f
        bitmapDst = BitmapFactory.decodeResource(resources,R.drawable.indicator)
        val bitmapDstHeight = width * f
        val bitmapDstWidth = bitmapDstHeight * bitmapDst.width / bitmapDst.height

    }

    private fun getSectorClip(r: Float,angle:Float,sweep:Float){
        arcPath.reset()
        arcPath.addArc(-r,-r,r,r,angle,sweep)
        arcPath.lineTo(0f,0f)
        arcPath.close()
    }
}
