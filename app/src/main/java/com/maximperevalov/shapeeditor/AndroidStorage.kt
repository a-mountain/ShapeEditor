package com.maximperevalov.shapeeditor

import android.util.Log
import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.Storage
import com.maximperevalov.shapeeditor.domain.shapes.*
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import java.io.File

class AndroidStorage(private val file: File) : Storage {

    override fun saveShapes(shapes: List<Shape>) {
        file.writeText("")
        shapes.forEach {
            val str = (serializeShape(it))
            file.appendText(str)
//            Log.d("AndroidStorage", str)
            file.appendText("\n")
        }
    }

    override fun getAllSavedShapes(): List<Shape> {
        val lines = file.readLines()
        Log.d("AndroidStorage", "Lines: $lines")
        return lines.map { deserializeShape(it) }
    }

    private fun deserializeStyle(style: Style) = listOf(
        style.isAbsoluteTransparent,
        style.isStrokeless,
        style.fillColor,
        style.stroke.hasDash,
        style.stroke.color,
        style.stroke.width
    ).serizlise()


    private fun serializeStyle(props: List<String>, offset: Int): Style {
        val isAbsoluteTransparent = props[offset]
        val isStrokeless = props[offset + 1]
        val fillColor = props[offset + 2]
        val strokeHasDash = props[offset + 3]
        val strokeColor = props[offset + 4]
        val strokeWidth = props[offset + 5]
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

    private fun serializeShape(shape: Shape): String {
        val style = deserializeStyle(shape.style)
        val props = ArrayList<Any>()
        when (shape) {
            is Circle -> {
                with(props) {
                    add("Circle")
                    add(shape.centerX)
                    add(shape.centerY)
                    add(shape.radius)
                }
            }
            is Cube -> {
                with(props) {
                    add("Cube")
                    add(shape.x1)
                    add(shape.y1)
                    add(shape.x2)
                    add(shape.y2)

                }
            }
            is Ellipse -> {
                with(props) {
                    add("Ellipse")
                    add(shape.x)
                    add(shape.y)
                    add(shape.width)
                    add(shape.height)
                }
            }
            is Line -> {
                with(props) {
                    add("Line")
                    add(shape.startX)
                    add(shape.startY)
                    add(shape.endX)
                    add(shape.endY)
                }
            }
            is LineWithDoubleCircle -> {
                with(props) {
                    add("LineWithDoubleCircle")
                    add(shape.startX)
                    add(shape.startY)
                    add(shape.endX)
                    add(shape.endY)
                    add(shape.radius)
                }
            }
            is Point -> {
                with(props) {
                    add("Point")
                    add(shape.x)
                    add(shape.y)
                }
            }
            is Rectangle -> {
                with(props) {
                    add("Rectangle")
                    add(shape.x)
                    add(shape.y)
                    add(shape.width)
                    add(shape.height)
                }
            }

        }
        props.add(style)
        Log.d("AndroidStorage", "ser Shape: ${props.serizlise()}")
        return props.serizlise()
    }

    private fun deserializeShape(string: String): Shape {
        val props = string.split("\t")
        Log.d("AndroidStorage", "des Shape: $props")
        return when (props[0]) {
            "Circle" -> {
                val centerX = props[1].toFloat()
                val centerY = props[2].toFloat()
                val radius = props[3].toFloat()
                val style = serializeStyle(props, 4)
                Circle(centerX, centerY, radius, style)
            }
            "Cube" -> {
                val x1 = props[1].toFloat()
                val y1 = props[2].toFloat()
                val x2 = props[3].toFloat()
                val y2 = props[4].toFloat()
                val style = serializeStyle(props, 5)
                Cube(x1, y1, x2, y2, style)
            }
            "Ellipse" -> {
                val x = props[1].toFloat()
                val y = props[2].toFloat()
                val width = props[3].toFloat()
                val height = props[4].toFloat()
                val style = serializeStyle(props, 5)
                Ellipse(x, y, width, height, style)
            }
            "Line" -> {
                val startX = props[1].toFloat()
                val startY = props[2].toFloat()
                val endX = props[3].toFloat()
                val endY = props[4].toFloat()
                val style = serializeStyle(props, 5)
                Line(startX, startY, endX, endY, style)
            }
            "LineWithDoubleCircle" -> {
                val startX = props[1].toFloat()
                val startY = props[2].toFloat()
                val endX = props[3].toFloat()
                val endY = props[4].toFloat()
                val radius = props[5].toFloat()
                val style = serializeStyle(props, 6)
                LineWithDoubleCircle(startX, startY, endX, endY, radius, style)
            }
            "Point" -> {
                val x = props[1].toFloat()
                val y = props[2].toFloat()
                val style = serializeStyle(props, 3)
                Point(x, y, style)
            }
            "Rectangle" -> {
                val x = props[1].toFloat()
                val y = props[2].toFloat()
                val width = props[3].toFloat()
                val height = props[4].toFloat()
                val style = serializeStyle(props, 5)
                Rectangle(x, y, width, height, style)
            }
            else -> throw RuntimeException("Can't deserialize shape: $props")
        }
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

    private fun List<Any>.serizlise() = joinToString(separator = "\t") { it.toString() }
}

