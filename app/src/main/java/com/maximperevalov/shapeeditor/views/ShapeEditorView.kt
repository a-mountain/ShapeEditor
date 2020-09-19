package com.maximperevalov.shapeeditor.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import com.maximperevalov.shapeeditor.AndroidDrawer
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.ShapeEditor

/**
 * View яке репрезентує редактор фігур
 */
class ShapeEditorView : View {

    private val bitmapPaint = Paint()
    private lateinit var bitmap: Bitmap

    private lateinit var shapeEditor: ShapeEditor

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context) : super(context)

    fun init(metrics: DisplayMetrics) {

        val height = metrics.heightPixels
        val width = metrics.widthPixels

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        shapeEditor = ShapeEditor(bitmap.width, bitmap.height, AndroidDrawer(canvas))

    }

    override fun onDraw(canvas: Canvas) {
        shapeEditor.draw()
        canvas.save()
        canvas.drawBitmap(bitmap, 0F, 0F, bitmapPaint);
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
