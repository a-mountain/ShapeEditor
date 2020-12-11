package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Line

class LineConverter : ShapeConverter<Line>() {
    override fun serializeWithoutStyle(shape: Line): String {
        return serialize("Line", shape.startX, shape.startY, shape.endX, shape.endY)
    }

    override fun deserialize(props: List<String>): Line {
        val startX = props[1].toFloat()
        val startY = props[2].toFloat()
        val endX = props[3].toFloat()
        val endY = props[4].toFloat()
        val style = serializeStyle(props, 5)
        return Line(startX, startY, endX, endY, style)
    }
}