package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.view.PixelCopy
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.palette.graphics.Palette
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mode = getDefaultNightMode()
        Log.d("xcl", "mode $mode")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            test()
        }
        btn.setOnClickListener {
            bitmapCal()
//            val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
//            val cm = ComponentName("com.android.settings","com.android.settings.Settings${'$'}DarkModeActivity")
//            intent.setComponent(cm);
//            intent.setAction("android.intent.action.VIEW")
//            startActivity(intent)
//            convertLayoutToBitmap(this)
        }

    }

    fun isForceDark(context: Context) {
        val value = TypedValue()
//        context.theme.resolveAttribute()
//        val id = context.resources.getIdentifier("forceDarkAllowed", "boolean","com.android")
//         val forceDark = context.theme.resolveAttribute(
//             id,
//             value,
//             true
//         ) && value.data != 0

//         val forceDark = context.theme.resolveAttribute(
//            android.R.attr.forceDarkAllowed,
//            value,
//            true
//        ) && value.data != 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun test() {
        tvName.post {
            //  val bitmap = viewToImage(tvName)
//            drawing.setImageBitmap(bitmap)
//            bitmapCal()
//            convertLayoutToBitmap(this)
        }
    }

    fun bitmapCal() {
        // 设置控件允许绘制缓存
        btn.setDrawingCacheEnabled(true)
        btn.buildDrawingCache()
        // 获取控件的绘制缓存（快照）
        var bitmap = btn.getDrawingCache()
        val half = btn.getWidth() shr 1
//        val dp10 = SystemUtils.dip2px(100)
//        截取部分区域
        val bitmap1 = Bitmap.createBitmap(bitmap, half, 0, half, btn.getHeight())
        btn.isDrawingCacheEnabled = false
        drawing.setImageBitmap(bitmap1)
//        drawing.setBackgroundColor(Color.YELLOW)
//        val bitmap = viewToImage(btn)
//        drawing.setImageBitmap(bitmap)
//        val bitmap = getBitmapFromView(btn)
        compareSkinColorWithViewColor(bitmap)
    }

    fun convertLayoutToBitmap(activity: Activity?) {
        if (activity == null || activity.isFinishing) {
            return
        }
        val window = activity.window
        val view = window.decorView
        val rect = Rect(0, 0, 500, 500)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dest = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.RGB_565)
            PixelCopy.request(
                window, rect, dest,
                { copyResult ->
                    if (copyResult == PixelCopy.SUCCESS) {
                        drawing.setImageBitmap(dest)
                    }
                }, Handler(Looper.getMainLooper())
            )
        } else {
            drawing.setImageBitmap(getBitmapFromView(view))
        }
    }

    /**
     * API26之前，把View转成Bitmap的方式
     */
    fun getBitmapFromView(v: View): Bitmap {
        v.isDrawingCacheEnabled = true //注意child 如果设置了 enable可能会导致获取的截图不更新。可循环遍历看看是哪个 view
        val drawingCache = v.drawingCache
        val bitmap = Bitmap.createBitmap(drawingCache)
        v.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun viewToImage(view: View): Bitmap {
        val returnedBitmap =
            Bitmap.createBitmap(view.measuredWidth, view.measuredWidth, Bitmap.Config.RGB_565)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null)
            bgDrawable.draw(canvas)
        view.draw(canvas)
        return returnedBitmap
    }

    private fun compareSkinColorWithViewColor(resource: Bitmap) {
//        val colorInt = resource.getPixel(10, 10)
//        val gray =
//            (Color.red(colorInt) * 0.299 + Color.green(colorInt) * 0.587 + Color.blue(colorInt) * 0.114)
//        val color: Int
//        val str = if (gray >= 192) "暗色" else "白色"
//        if (gray >= 192) {
//            // 深色系
//            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
//        } else {
//            // 浅色系
//            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
//        }
//    }
        val pixel = resource.getPixel(0, 0)
        drawing.setBackgroundColor(pixel)
        val palette = Palette.Builder(resource)
            .generate()
//        val palette = Palette.generate(resource,24)
        var viewInLightMode = false
        if(palette.dominantSwatch == null){
            viewInLightMode = true
        }
        val str = if (viewInLightMode) "白色" else "暗色"
        Toast.makeText(this, str,Toast.LENGTH_LONG).show()
    }

}
