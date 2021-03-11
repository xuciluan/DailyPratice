package com.example.myapplication.pullleftLoadmore

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class PullLeftLoadMoreLayout  @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?, style: Int = 0) :
    CoordinatorLayout(context, attributeSet, style){

    private var loadHeight = 0
    private lateinit var txt :TextView
    private lateinit var loadingView: LoadingView
    private val anim = ValueAnimator.ofFloat(0f,1f)
    private var mTranslation = 0f
    private var inAnim = false

    init {
        anim.duration = 500
        anim.run {
            addListener(object:Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    mTranslation = 0f
                    removeAllUpdateListeners()
                    inAnim  = false
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {
                    inAnim  = true
                }

            })
        }
    }

    public fun addView(height : Int){
        this.loadHeight = height
        val layoutParams = ViewGroup.LayoutParams(height,height)

        loadingView = LoadingView(context)
        loadingView.layoutParams = layoutParams
        addView(loadingView)

        txt = TextView(context)
        txt.layoutParams = layoutParams
        txt.text = "查看更多"
        txt.setEms(1)
        txt.textSize = 10f
        txt.setTextColor(Color.BLACK)
        txt.gravity = Gravity.CENTER
        addView(txt)
        list = getChildAt(0)
    }

    private lateinit var list : View
    override fun onLayoutChild(child: View, layoutDirection: Int) {
        super.onLayoutChild(child, layoutDirection)
        if(child == txt){
            //文本
            val txtWidth = child.width
            child.offsetLeftAndRight(width - txtWidth)
            child.translationX = txtWidth.toFloat() //隐藏View
            child.offsetTopAndBottom(height / 2 - txtWidth / 2)
            txt.setText("查看更多")
        }else if(child == loadingView){
            //列表
            child.offsetLeftAndRight(width - loadingView.width)
//            child.translationX = loadingView.width.toFloat()
        }
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
//        return super.onStartNestedScroll(child, target, axes, type)
        val scroll = (axes and  ViewCompat.SCROLL_AXIS_HORIZONTAL )!=0
//        Log.d("xcl", "scroll $scroll ")
        return scroll && !inAnim
    }



    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
        //dx = 起点 - 终点
        if(dx > 0  && !list.canScrollHorizontally(ViewCompat.SCROLL_INDICATOR_LEFT)//向左滑动 & 列表无法再动了
            || (dx < 0 && list.translationX < 0)){
            consumed[0] = dx //全部自己消费

            val distance = dx / 2.0f
            if(mTranslation - distance < -loadHeight){  //向左滑动的距离超过的右边的滑块宽度
                mTranslation = -loadHeight.toFloat()
            }else{
                mTranslation = mTranslation - distance
            }

//            Log.d("xcl", "translation = $mTranslation , dx = $dx")
            list.translationX = mTranslation

            val fraction = Math.abs(mTranslation / loadingView.width)
            loadingView.setFraction(fraction.toFloat(),mTranslation / 2)
            translateTxt(mTranslation)
//            loadingView.translationX = loadingView.width + translation
//            txt.translationX = txt.width + translation
        }else{
            //让列表消费
//            Log.d("xcl", "让列表消费")
            consumed[0]= 0
        }
    }

    private var go  = false
    private fun translateTxt(translation : Float){
        val distance = translation / 2
        Log.d("xcl" ,"distance = " + distance + ",-txt.width /3*4 = " + (-txt.width /3*4))
        if(distance <= -txt.width /3*4){
            Log.d("xcl","可以起飞了~")
            //左边
            go = true
            txt.setText("释放加载")
            txt.translationX = - 1 / 3 * txt.width.toFloat()
        }else{
            go = false
            txt.setText("查看更多")
            txt.translationX = distance + txt.width //要加上原来的距离
        }
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        super.onStopNestedScroll(target, type)
        if(mTranslation != 0f){
            anim.addUpdateListener(object:ValueAnimator.AnimatorUpdateListener{
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val tras = (1 - animation!!.animatedFraction) * mTranslation
                    list.translationX = tras
                    val fraction = Math.abs(tras / loadingView.width)
                    loadingView.setFraction(fraction.toFloat(),tras / 2)
                    translateTxt(tras)
                }
            })
            anim.start()
            if(go){
                Toast.makeText(context,"起飞",Toast.LENGTH_SHORT).show()
            }
        }
    }

}