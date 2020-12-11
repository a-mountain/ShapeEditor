package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Rectangle

class RectangleConverter : ShapeConverter<Rectangle>() {
    override fun serializeWithoutStyle(shape: Rectangle): String {
        return serialize("Rectangle", shape.x, shape.y, shape.width, shape.height)
    }

    override fun deserialize(props: List<String>): Rectangle {
        val x = props[1].toFloat()
        val y = props[2].toFloat()
        val width = props[3].toFloat()
        val height = props[4].toFloat()
        val style = serializeStyle(props, 5)
        return Rectangle(x, y, width, height, style)
    }
}