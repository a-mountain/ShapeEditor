package com.maximperevalov.shapeeditor.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import com.maximperevalov.shapeeditor.AndroidCanvasDrawer
import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_STYLE
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.ShapeEditor

/**
 * Графічне представлення полотна, для малювання фігури.
 */
class ShapeEditorView : View {

    private val bitmapPaint = Paint()
    private lateinit var bitmap: Bitmap

    private lateinit var shapeEditor: ShapeEditor

    var shapeStrokeColor: Color
        get() = shapeEditor.currentShapeStyle.stroke?.color ?: DEFAULT_STYLE.stroke!!.color
        set(value) {
            shapeEditor.currentShapeStyle.stroke?.color = value
        }

    var shapeFillColor: Color
        get() = shapeEditor.currentShapeStyle.fillColor ?: DEFAULT_STYLE.fillColor!!
        set(value) {
            shapeEditor.currentShapeStyle.fillColor = value
        }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context) : super(context)

    fun init(metrics: DisplayMetrics) {

        val height = metrics.heightPixels
        val width = metrics.widthPixels

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val drawer = AndroidCanvasDrawer(canvas)
        shapeEditor = ShapeEditor(bitmap.width, bitmap.height, drawer)
    }

    override fun onDraw(canvas: Canvas) {
        shapeEditor.draw()
        canvas.save()
        canvas.drawBitmap(bitmap, 0F, 0F, bitmapPaint)
        canvas.restore()
    }

    fun clearLastShape() {
        shapeEditor.clearLastShape()
        invalidate()
    }

    fun clearAllShapes() {
        shapeEditor.clearAllShapes()
        invalidate()
    }

    fun selectShape(selectedShape: SelectedShape) {
        shapeEditor.selectShape(selectedShape)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> shapeEditor.onFirstTouch(event.x, event.y)
            MotionEvent.ACTION_MOVE -> shapeEditor.onMove(event.x, event.y)
            MotionEvent.ACTION_UP -> shapeEditor.onLastTouch(event.x, event.y)
        }
        invalidate()
        return true
    }
}
