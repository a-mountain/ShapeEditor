package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Circle

class CircleConverter : ShapeConverter<Circle>() {
    override fun serializeWithoutStyle(shape: Circle): String {
        return serialize("Circle", shape.centerX, shape.centerY, shape.radius)
    }

    override fun deserialize(props: List<String>): Circle {
        val centerX = props[1].toFloat()
        val centerY = props[2].toFloat()
        val radius = props[3].toFloat()
        val style = serializeStyle(props, 4)
        return Circle(centerX, centerY, radius, style)
    }
}