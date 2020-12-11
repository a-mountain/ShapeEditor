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
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.Storage
import com.maximperevalov.shapeeditor.domain.editor.EditorEvent
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.events.EditorEventHandler

/**
 * Графічне представлення полотна, для малювання фігури.
 */
class ShapeEditorView : View {

    private val bitmapPaint = Paint()
    private lateinit var bitmap: Bitmap

    lateinit var shapeEditor: ShapeEditor

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context) : super(context)

    fun addEventHandler(event: EditorEvent, eventHandler: EditorEventHandler) {
        shapeEditor.addEventHandler(event, eventHandler)
    }

    fun init(metrics: DisplayMetrics) {

        val height = metrics.heightPixels
        val width = metrics.widthPixels

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val drawer = AndroidCanvasDrawer(canvas)
        shapeEditor = ShapeEditor.getInstance()
        shapeEditor.init(
            bitmap.width,
            bitmap.height,
            drawer,
            object : Storage {
                override fun saveShapes(shapes: List<Shape>) {

                }

                override fun getAllSavedShapes(): List<Shape> {
                    return emptyList()
                }

            }
        )
    }

    override fun onDraw(canvas: Canvas) {
        shapeEditor.draw()
        canvas.save()
        canvas.drawBitmap(bitmap, 0F, 0F, bitmapPaint)
        canvas.restore()
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
