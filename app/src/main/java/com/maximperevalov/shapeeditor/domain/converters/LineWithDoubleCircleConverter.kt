package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.LineWithDoubleCircle

class LineWithDoubleCircleConverter : ShapeConverter<LineWithDoubleCircle>() {
    override fun serializeWithoutStyle(shape: LineWithDoubleCircle): String {
        return serialize(
            "LineWithDoubleCircle",
            shape.startX,
            shape.startY,
            shape.endX,
            shape.endY,
            shape.radius
        )
    }

    override fun deserialize(props: List<String>): LineWithDoubleCircle {
        val startX = props[1].toFloat()
        val startY = props[2].toFloat()
        val endX = props[3].toFloat()
        val endY = props[4].toFloat()
        val radius = props[5].toFloat()
        val style = serializeStyle(props, 6)
        return LineWithDoubleCircle(startX, startY, endX, endY, radius, style)
    }
}