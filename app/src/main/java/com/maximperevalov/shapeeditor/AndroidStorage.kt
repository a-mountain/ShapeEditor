package com.maximperevalov.shapeeditor

import android.util.Log
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.Storage
import com.maximperevalov.shapeeditor.domain.converters.*
import com.maximperevalov.shapeeditor.domain.shapes.*
import java.io.File

class AndroidStorage(private val file: File) : Storage {

    override fun saveShapes(shapes: List<Shape>) {
        file.writeText("")
        shapes.forEach {
            val str = (serializeShape(it))
            file.appendText(str)
            file.appendText("\n")
        }
    }

    private fun serializeShape(shape: Shape): String {
        return when (shape) {
            is Circle -> CircleConverter().serialize(shape)
            is Cube -> CubeConverter().serialize(shape)
            is Ellipse -> EllipseConverter().serialize(shape)
            is Line -> LineConverter().serialize(shape)
            is LineWithDoubleCircle -> LineWithDoubleCircleConverter().serialize(shape)
            is Point -> PointConverter().serialize(shape)
            is Rectangle -> RectangleConverter().serialize(shape)
            else -> throw RuntimeException("Has no converter for this class: ${shape::class.java}")
        }
    }

    private fun deserializeShape(string: String): Shape {
        val props = string.split("\t")
        val type = props[0]
        return when (type) {
            "Circle" -> CircleConverter().deserialize(props)
            "Cube" -> CubeConverter().deserialize(props)
            "Ellipse" -> EllipseConverter().deserialize(props)
            "Line" -> LineConverter().deserialize(props)
            "LineWithDoubleCircle" -> LineWithDoubleCircleConverter().deserialize(props)
            "Point" -> PointConverter().deserialize(props)
            "Rectangle" -> RectangleConverter().deserialize(props)
            else -> throw RuntimeException("Has no converter for this class: ${props[0]}")
        }
    }

    override fun getAllSavedShapes(): List<Shape> {
        val lines = file.readLines()
        Log.d("AndroidStorage", "Lines: $lines")
        return lines.map { deserializeShape(it) }
    }
}
