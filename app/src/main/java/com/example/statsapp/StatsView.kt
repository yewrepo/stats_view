package com.example.statsapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    c: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(c, attrs, defStyleAttr, defStyleRes) {

    var data: List<Float> = emptyList()
        set(value) {
            field = value
            invalidate()
        }

    private var oval: RectF = RectF()
    private var center = PointF(0f, 0f)

    private var radius = 0f
    private var lineWidth = 5.px(context).toFloat()
    private var fontSize = 40.px(context).toFloat()
    private var colors = emptyList<Int>()

    init {
        context.withStyledAttributes(attrs, R.styleable.StatsView) {
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth)
            fontSize = getDimension(R.styleable.StatsView_fontSize, fontSize)

            val resId = getResourceId(R.styleable.StatsView_colors, 0)
            colors = resources.getIntArray(resId).toList()
        }
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = fontSize
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        center = PointF(w / 2f, h / 2f)
        radius = Math.min(w, h) / 2f - lineWidth / 2f
        oval = RectF(
            center.x - radius, center.y - radius,
            center.x + radius, center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        if (data.isNotEmpty()) {
            var startFrom = -90f

            canvas?.drawText(
                "%.2f%%".format(data.sum() * 100),
                center.x,
                center.y + textPaint.textSize / 4,
                textPaint
            )

            for (datum in data) {
                val angle = 360f * datum
                paint.color =
                    colors.getOrNull(Random.nextInt(0, colors.size - 1)) ?: getRandomColor()
                canvas?.drawArc(oval, startFrom, angle, false, paint)
                startFrom += angle
            }
        }
    }

    private fun getRandomColor(): Int {
        return Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
    }
}