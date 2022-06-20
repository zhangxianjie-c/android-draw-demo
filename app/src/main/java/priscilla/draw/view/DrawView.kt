package priscilla.draw.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import priscilla.draw.R

/**
Created by Zebra-RD张先杰 on 2022年6月20日16:58:23

Description:
 */
class DrawView : View {
    public val DRAW_CIRCLE: Int = 1

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        obtainAttributes(context, attributeSet)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)
    private var mDrawColor:Int = Resources.getSystem().configuration.
    private var mDrawType = DRAW_CIRCLE
    private fun obtainAttributes(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.DrawView)
        //画的形状
        mDrawType = when (ta.getString(R.styleable.DrawView_type)) {
            "circle" -> DRAW_CIRCLE
            else -> DRAW_CIRCLE
        }
        mDrawColor = ta.getColor(R.styleable.DrawView_color,ResourcesCompat.getColor(resources,R.color.black,null))
        ta.recycle()
    }

    /**
     * 用于绘制的画笔
     */
    private val drawPaint: Paint by lazy {
        Paint().apply {
            color = mDrawColor
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (mDrawType) {
            DRAW_CIRCLE -> {
                //cx:圆心的x轴,cy:圆心的y轴,radius:圆的半径,paint:Paint()
                canvas.drawCircle(width / 2.toFloat(),
                    height / 2.toFloat(),
                    if (width < height) width / 2.toFloat() else height / 2.toFloat(),
                    drawPaint)
            }

        }
    }
}