package com.example.myapplication

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.TextView

/**
 *
 * @Author chrisxu
 * @Date 2021/2/3
 * @Version
 **/
class MyButton (context: Context, attributeSet: AttributeSet?, style:Int) : FrameLayout(context,attributeSet,style),
    View.OnTouchListener{

    constructor(context: Context, attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)

    private lateinit var drawable: GradientDrawable
    private lateinit var colorAnim : ValueAnimator
    private lateinit var scaleAnim : ScaleAnimation
    private lateinit var text : String
    private val touchColorResource = R.drawable.yellow_pressed
    private val normalResource = R.drawable.yellow_normal
    init {
        val array = getContext().obtainStyledAttributes(attributeSet, R.styleable.MyButton)
        text = array.getString(R.styleable.MyButton_a6btn_text)?:""
        array.recycle()
        init()
    }

    private fun init(){
        removeAllViews()
        initButton()
        setOnTouchListener(this)
    }

    private fun initButton(){
        drawable = GradientDrawable()
        drawable.cornerRadius = dip2px(context,6f).toFloat()
        drawable.setColor(getResources().getColor(R.color.main_bt_color))
        drawable.setStroke(1,resources.getColor(R.color.btn_stork_color))
        background = drawable

        val tv = TextView(context)
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        lp.gravity = Gravity.CENTER
        tv.layoutParams = lp
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.WHITE)
        tv.textSize = 33f
        tv.text = text

        addView(tv)
        initAnim()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mlp = layoutParams as MarginLayoutParams
        val margin = dip2px(context,9f)
        mlp.leftMargin  = margin
        mlp.rightMargin = margin
        mlp.topMargin  = margin
        mlp.bottomMargin = margin
        layoutParams = mlp
        setMeasuredDimension(dip2px(context,340f),
            dip2px(context,75f))
    }

    fun initAnim(){
       scaleAnim = MyAnimUtils.getScaleAnim()
       val pressedColor = getResources().getColor(R.color.main_bt_trigger_color)
       val touchColor = getResources().getColor(R.color.main_bt_press_color)
       colorAnim = MyAnimUtils.getColorAnim(pressedColor,touchColor)
       colorAnim.addUpdateListener {
           val color = it.animatedValue as Int
           drawable.setColor(color)
           this.background = drawable
       }
       colorAnim.addListener(object: Animator.AnimatorListener {
           override fun onAnimationRepeat(animation: Animator?) {

           }

           override fun onAnimationEnd(animation: Animator?) {
               MyTouchManager.disableAllClick = false
               performClick()
               Handler().post {
                   setBackgroundResource(R.drawable.yellow_normal)
               }
           }

           override fun onAnimationCancel(animation: Animator?) {
               MyTouchManager.disableAllClick = false
           }

           override fun onAnimationStart(animation: Animator?) {
               MyTouchManager.disableAllClick = true
           }
       })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                setBackgroundResource(touchColorResource)
            }

           MotionEvent.ACTION_UP ->{
                val x = v?.x?: 0f
                val y  = v?.y?: 0f

                if(x < 0 || x > width  || y < 0 || y > height){
                    event.action = MotionEvent.ACTION_CANCEL
                    return onTouch(v,event)
                }else if(!colorAnim.isRunning){
                    v?.startAnimation(scaleAnim)
                    colorAnim.start()
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if(x < 0 || x > width  || y < 0 || y > height){
                    event.action = MotionEvent.ACTION_CANCEL
                    return onTouch(v,event)
                }
            }

            MotionEvent.ACTION_CANCEL -> {
                setBackgroundResource(normalResource)
            }
        }
        return true
    }


}