package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Point

class PointConverter : ShapeConverter<Point>() {
    override fun serializeWithoutStyle(shape: Point): String {
        return serialize("Point", shape.x, shape.y)
    }

    override fun deserialize(props: List<String>): Point {
        val x = props[1].toFloat()
        val y = props[2].toFloat()
        val style = serializeStyle(props, 3)
        return Point(x, y, style)
    }
}