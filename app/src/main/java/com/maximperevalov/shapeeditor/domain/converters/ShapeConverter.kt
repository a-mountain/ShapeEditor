package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

abstract class ShapeConverter<T : Shape> {

    protected abstract fun serializeWithoutStyle(shape: T): String

    fun serialize(shape: T): String {
        val style = deserializeStyle(shape.style)
        return serializeWithoutStyle(shape) + "\t" + style
    }

    abstract fun deserialize(props: List<String>): T

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

    private fun deserializeStyle(style: Style) = serialize(
        style.isAbsoluteTransparent,
        style.isStrokeless,
        style.fillColor,
        style.stroke.hasDash,
        style.stroke.color,
        style.stroke.width
    )


    protected fun serializeStyle(props: List<String>, offset: Int): Style {
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

    protected fun serialize(vararg props: Any) =
        props.joinToString(separator = "\t") { it.toString() }
}

