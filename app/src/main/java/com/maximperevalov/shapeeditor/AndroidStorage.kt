package com.maximperevalov.shapeeditor

import android.util.Log
import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.Storage
import com.maximperevalov.shapeeditor.domain.helpers.distance
import com.maximperevalov.shapeeditor.domain.shapes.*
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import java.io.File

class AndroidStorage(private val file: File) : Storage {

    override fun saveShapes(shapes: List<Shape>) {
        file.bufferedWriter().write("")
        shapes.forEach {
            val str = (shapeToString(it))
            Log.d("AndroidStorage", str)
            file.bufferedWriter().appendLine(str)
        }
    }

    override fun getAllSavedShapes() = file.readLines().map { getShapeFromString(it) }

    private fun shapeToString(shape: Shape): String {
        var type = ""
        var x1 = 0F
        var y1 = 0F
        var x2 = 0F
        var y2 = 0F
        val style = styleToString(shape.style)
        when (shape) {
            is Circle -> {
                type = "Circle"
                x1 = shape.centerX
                y1 = shape.centerY
                x2 = shape.centerY + shape.radius
                y2 = shape.centerY + shape.radius
            }
            is Cube -> {
                type = "Cube"
                x1 = shape.x1
                y1 = shape.y1
                x2 = shape.x2
                y2 = shape.y2
            }
            is Ellipse -> {
                type = "Ellipse"
                x1 = shape.x
                y1 = shape.y
                x2 = shape.x + shape.width
                y2 = shape.x + shape.height
            }
            is Line -> {
                type = "Line"
                x1 = shape.startX
                y1 = shape.startY
                x2 = shape.endX
                y2 = shape.endY
            }
            is LineWithDoubleCircle -> {
                type = "LineWithDoubleCircle"
                x1 = shape.startX
                y1 = shape.startY
                x2 = shape.endX
                y2 = shape.endY
            }
            is Point -> {
                type = "Point"
                x1 = shape.x
                y1 = shape.y
                x2 = shape.x
                y2 = shape.y
            }
            is Rectangle -> {
                type = "Rectangle"
                x1 = shape.x
                y1 = shape.y
                x2 = shape.x + shape.width
                y2 = shape.x + shape.height
            }

        }
        return buildString {
            append(type)
            append("\t")
            append(x1)
            append("\t")
            append(y1)
            append("\t")
            append(x2)
            append("\t")
            append(y2)
            append("\t")
            append(style)
        }
    }

    private fun styleToString(style: Style) = buildString {
        append(style.isAbsoluteTransparent)
        append("\t")
        append(style.isStrokeless)
        append("\t")
        append(style.fillColor.toString())
        append("\t")
        append(style.stroke.hasDash)
        append("\t")
        append(style.stroke.color.toString())
        append("\t")
        append(style.stroke.width)
    }

    private fun getShapeFromString(stringShape: String): Shape {
        val properties = stringShape.split("\t")
        val type = properties[0]
        val x1 = properties[1].toFloat()
        val y1 = properties[2].toFloat()
        val x2 = properties[3].toFloat()
        val y2 = properties[4].toFloat()
        val style = getStyleFromString(properties)
        return when (type) {
            "Circle" -> Circle(x1, y1, distance(x1, y1, x2, y2), style)
            "Cube" -> Cube(x1, y1, x2, y2, style)
            "Ellipse" -> Ellipse(x1, y1, x2 - x1, y2 - y1, style)
            "Line" -> Line(x1, y1, x2, y2, style)
            "LineWithDoubleCircle" -> LineWithDoubleCircle(x1, y1, x2, y2, 100F, style)
            "Point" -> Point(x1, y1, style)
            else -> Rectangle(x1, y1, x2 - x1, y2 - y1, style)
        }
    }

    private fun getStyleFromString(properties: List<String>): Style {
        val isAbsoluteTransparent = properties[5]
        val isStrokeless = properties[6]
        val fillColor = properties[7]
        val strokeHasDash = properties[8]
        val strokeColor = properties[9]
        val strokeWidth = properties[10]
        return Style(
            getColorFromString(fillColor),
            Stroke(
                strokeWidth.toFloat(),
                getColorFromString(strokeColor),
                strokeHasDash.toBoolean()
            ),
            isAbsoluteTransparent.toBoolean(),
            isStrokeless.toBoolean()
        )
    }

    private fun getColorFromString(string: String) = when (string) {
        "RED" -> Color.RED
        "BLUE" -> Color.BLUE
        "YELLOW" -> Color.BLACK
        "BLACK" -> Color.BLACK
        "WHITE" -> Color.WHITE
        "ORANGE" -> Color.ORANGE
        "GRAY" -> Color.ORANGE
        "GREEN" -> Color.GREEN
        "PURPLE" -> Color.PURPLE
        else -> Color.PINK
    }
}

