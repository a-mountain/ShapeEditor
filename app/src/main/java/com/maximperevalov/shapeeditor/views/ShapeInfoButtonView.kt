package com.maximperevalov.shapeeditor.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import com.maximperevalov.shapeeditor.AndroidCanvasDrawer
import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.Ellipse
import com.maximperevalov.shapeeditor.domain.shapes.Line
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private const val STROKE_WIDTH = 7F

/**
 * Прямокутна кнопка, з вибраною фігурою для малювання.
 * Це фігура має зменшений розмір і такі самі параметри кольор,
 * як і дійсна фігура, яка буде малюватися.
 */
class ShapeInfoButtonView(context: Context, attributes: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageButton(context, attributes) {

    var style = Style(Color.BLACK, Stroke(STROKE_WIDTH, Color.BLACK))
        set(value) {
            field = value
            draw()
        }

    private lateinit var shapeFactory: ShapeFactory
    private lateinit var bitmap: Bitmap

    private lateinit var drawer: AndroidCanvasDrawer
    private lateinit var shape: Shape

    private lateinit var background: Rectangle

    fun init(width: Int, height: Int, padding: Float) {
        isClickable = false
        background = Rectangle(
            0F,
            0F,
            width.toFloat(),
            height.toFloat(),
            Style(fillColor = Color.WHITE, Stroke(10F, Color.BLACK))
        )

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        shapeFactory = ShapeFactory(width.toFloat(), height.toFloat(), padding, style)
        drawer = AndroidCanvasDrawer(canvas)

        setSelectedShape(SelectedShape.ELLIPSE)
    }

    fun setSelectedShape(selectedShape: SelectedShape) {
        shape = shapeFactory.getShape(selectedShape)
        draw()
    }

    private fun drawBackground() {
        background.draw(drawer)
    }

    private fun draw() {
        drawBackground()
        applyCurrentStyleToShape()
        shape.draw(drawer)
        setImageBitmap(bitmap)
    }

    private fun applyCurrentStyleToShape() {
        shape.style = style.withStrokeWidth(shape.style.stroke.width)
    }
}


private class ShapeFactory(width: Float, height: Float, padding: Float, style: Style) {

    private val widthWithPadding = width - padding
    private val heightWithPadding = height - padding

    private val line = Line(
        padding,
        padding,
        widthWithPadding,
        heightWithPadding,
        style.withStrokeWidth(7F)
    )
    private val rectangle = Rectangle(
        padding,
        padding,
        widthWithPadding,
        heightWithPadding,
        style.withStrokeWidth(5F),
    )
    private val ellipse =
        Ellipse(
            padding,
            padding,
            widthWithPadding,
            heightWithPadding,
            style.withStrokeWidth(5F)
        )
    private val point = Point(
        width / 2,
        height / 2,
        style.withStrokeWidth(7F),
    )

    fun getShape(selectedShape: SelectedShape) = when (selectedShape) {
        SelectedShape.LINE -> line
        SelectedShape.ELLIPSE -> ellipse
        SelectedShape.POINT -> point
        SelectedShape.RECTANGLE -> rectangle
    }
}
