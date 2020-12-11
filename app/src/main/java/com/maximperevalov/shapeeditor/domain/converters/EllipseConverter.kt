package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Ellipse

class EllipseConverter : ShapeConverter<Ellipse>() {
    override fun serializeWithoutStyle(shape: Ellipse): String {
        return serialize("Ellipse", shape.x, shape.y, shape.width, shape.height)
    }

    override fun deserialize(props: List<String>): Ellipse {
        val x = props[1].toFloat()
        val y = props[2].toFloat()
        val width = props[3].toFloat()
        val height = props[4].toFloat()
        val style = serializeStyle(props, 5)
        return Ellipse(x, y, width, height, style)
    }
}