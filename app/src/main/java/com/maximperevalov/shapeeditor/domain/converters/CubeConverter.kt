package com.maximperevalov.shapeeditor.domain.converters

import com.maximperevalov.shapeeditor.domain.shapes.Cube

class CubeConverter : ShapeConverter<Cube>() {
    override fun serializeWithoutStyle(shape: Cube): String {
        return serialize("Cube", shape.x1, shape.y1, shape.x2, shape.y2)
    }

    override fun deserialize(props: List<String>): Cube {
        val x1 = props[1].toFloat()
        val y1 = props[2].toFloat()
        val x2 = props[3].toFloat()
        val y2 = props[4].toFloat()
        val style = serializeStyle(props, 5)
        return Cube(x1, y1, x2, y2, style)
    }
}